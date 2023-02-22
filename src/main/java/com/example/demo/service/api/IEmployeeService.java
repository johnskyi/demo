package com.example.demo.service.api;

import com.example.demo.data.dto.EmployeeDto;
import com.example.demo.data.entity.Employee;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;

public interface IEmployeeService {
    Page<Employee> getAll(Predicate predicate, String sort, int page, int size);
    long create(EmployeeDto employeeDto);
    EmployeeDto getById(long id);
    long delete(long id);
    EmployeeDto update(EmployeeDto employeeDto);
}
