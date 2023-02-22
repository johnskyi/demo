package com.example.demo.validation;

import com.example.demo.controller.EmployeeController;
import com.example.demo.data.dto.EmployeeDto;
import com.example.demo.data.entity.Sex;
import com.example.demo.data.entity.Worth;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class ValidationTest {

    private static final String EMPLOYEE_TEST_NAME = "Name";
    private static final String EMPLOYEE_TEST_PATRONYMIC = "Patronymic";
    private static final String EMPLOYEE_TEST_SECOND_NAME = "SecondName";
    private static final String EMPLOYEE_TEST_EMAIL = "test@yamdex.ru";
    private static final String WORTH_TEST_NAME = "WorthName";
    private static final String WORTH_TEST_CATEGORY = "WorthCategory";

    @Autowired
    private EmployeeController employeeController;


    @Test
    void createWithInvalidPatronymicThrowsConstraintViolationException() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic("");
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.setWorths(new ArrayList<>());
        employeeDto.getWorths().add(worth);

        // When
        Throwable thrown = catchThrowable(() -> employeeController.create(employeeDto));

        // Then
        assertThat(thrown).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void createWithBlankSecondNameThrowsConstraintViolationException() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName("");
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.setWorths(new ArrayList<>());
        employeeDto.getWorths().add(worth);

        // When
        Throwable thrown = catchThrowable(() -> employeeController.create(employeeDto));

        // Then
        assertThat(thrown).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void createWithInvalidEmailThrowsConstraintViolationException() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail("test.ru");
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.setWorths(new ArrayList<>());
        employeeDto.getWorths().add(worth);

        // When
        Throwable thrown = catchThrowable(() -> employeeController.create(employeeDto));

        // Then
        assertThat(thrown).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void createWithEmptyNameThrowsConstraintViolationException() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("");
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        Worth worth = new Worth();
        worth.setName(WORTH_TEST_NAME);
        worth.setCategory(WORTH_TEST_CATEGORY);
        worth.setPrice(123L);
        worth.setDate(LocalDate.now());
        employeeDto.setWorths(new ArrayList<>());
        employeeDto.getWorths().add(worth);

        // When
        Throwable thrown = catchThrowable(() -> employeeController.create(employeeDto));

        // Then
        assertThat(thrown).isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    void createWithEmptyWorthsThrowsConstraintViolationException() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(EMPLOYEE_TEST_NAME);
        employeeDto.setEmail(EMPLOYEE_TEST_EMAIL);
        employeeDto.setSex(Sex.MALE);
        employeeDto.setSecondName(EMPLOYEE_TEST_SECOND_NAME);
        employeeDto.setPatronymic(EMPLOYEE_TEST_PATRONYMIC);
        employeeDto.setWorths(new ArrayList<>());

        // When
        Throwable thrown = catchThrowable(() -> employeeController.create(employeeDto));

        // Then
        assertThat(thrown).isInstanceOf(ConstraintViolationException.class);
    }
}