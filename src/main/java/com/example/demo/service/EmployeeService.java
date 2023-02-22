package com.example.demo.service;

import com.example.demo.data.dto.EmployeeDto;
import com.example.demo.data.entity.Employee;
import com.example.demo.data.repository.EmployeeRepository;
import com.example.demo.service.api.IEmployeeService;
import com.example.demo.service.exception.EntityNotFoundException;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Employee> getAll(Predicate predicate, String sort, int page, int size) {
        Page<Employee> employeePage = sort != null ? employeeRepository.findAll(predicate, PageRequest.of(page, size, Sort.by(sort)))
                : employeeRepository.findAll(predicate, PageRequest.of(page, size));
        return employeePage.getContent().isEmpty() ? Page.empty() : employeePage;
    }

    @Override
    @Transactional
    public long create(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.getWorths().forEach(worth -> worth.setEmployee(employee));
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee {} was created", employeeDto.getSecondName());
        return savedEmployee.getId();
    }

    @Override
    public EmployeeDto getById(long id) {
        return modelMapper.map(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id - " + id + " do not found")), EmployeeDto.class);
    }

    @Override
    @Transactional
    public long delete(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id - " + id + " do not found"));
        employeeRepository.delete(employee);
        log.info("Employee with id {} was deleted", id);
        return id;
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee with id - " + employeeDto.getId() + " do not found"));
        employee.getWorths().clear();
        employee.getWorths().addAll(employeeDto.getWorths());
        employee.setSex(employeeDto.getSex());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setName(employeeDto.getName());
        employee.setSecondName(employeeDto.getSecondName());
        employee.setEmail(employeeDto.getEmail());
        employeeRepository.save(employee);
        log.info("Employee {} was updated", employeeDto.getSecondName());
        return employeeDto;
    }
}
