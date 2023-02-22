package com.example.demo.service;

import com.example.demo.data.dto.EmployeeDto;
import com.example.demo.data.entity.Employee;
import com.example.demo.data.entity.Sex;
import com.example.demo.data.entity.Worth;
import com.example.demo.data.repository.EmployeeRepository;
import com.example.demo.service.api.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceTest {
    private static final String EMPLOYEE_TEST_NAME = "Name";
    private static final String EMPLOYEE_TEST_PATRONYMIC = "Patronymic";
    private static final String EMPLOYEE_TEST_SECOND_NAME = "SecondName";
    private static final String EMPLOYEE_TEST_EMAIL = "test@yamdex.ru";
    private static final String WORTH_TEST_NAME = "WorthName";
    private static final String WORTH_TEST_CATEGORY = "WorthCategory";
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void createEmployeeShouldExist() {
        // Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.getWorths().add(worth);

        // When
        Long savedEmployeeId = employeeService.create(employeeDto);
        Employee fetchedEmployee = employeeRepository.findById(savedEmployeeId).get();

        // Then
        assertThat(fetchedEmployee.getSecondName()).isEqualTo(EMPLOYEE_TEST_SECOND_NAME);
    }

    @Test
    void deleteEmployeeShouldDelete() {
        // Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.getWorths().add(worth);
        Long savedEmployeeId = employeeService.create(employeeDto);
        Employee fetchedEmployee = employeeRepository.findById(savedEmployeeId).get();

        // When
        employeeService.delete(fetchedEmployee.getId());
        Optional<Employee> employeesAfterDeleteOpt = employeeRepository.findById(savedEmployeeId);
        // Then
        assertThat(employeesAfterDeleteOpt).isEmpty();
    }

    @Test
    void updateEmployeeShouldUpdate() {
        // Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.getWorths().add(worth);
        Long savedEmployeeId = employeeService.create(employeeDto);
        Employee fetchedEmployee = employeeRepository.findById(savedEmployeeId).get();

        // When
        fetchedEmployee.setName("AnotherName");
        EmployeeDto updatedEmployeedto = employeeService.update(modelMapper.map(fetchedEmployee, EmployeeDto.class));
        // Then
        assertThat(updatedEmployeedto.getId()).isEqualTo(fetchedEmployee.getId());
    }

    @Test
    void getEmployeeByIdShouldTheSame() {
        // Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.getWorths().add(worth);
        Long savedEmployeeId = employeeService.create(employeeDto);

        // When
        EmployeeDto fetchedEmployeedto = employeeService.getById(savedEmployeeId);
        // Then
        assertThat(fetchedEmployeedto.getId()).isEqualTo(savedEmployeeId);
        assertThat(fetchedEmployeedto.getSecondName()).isEqualTo(employeeDto.getSecondName());
        assertThat(fetchedEmployeedto.getName()).isEqualTo(employeeDto.getName());
    }
}