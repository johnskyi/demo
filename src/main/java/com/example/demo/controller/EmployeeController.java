package com.example.demo.controller;

import com.example.demo.data.dto.EmployeeDto;
import com.example.demo.data.entity.Employee;
import com.example.demo.service.api.IEmployeeService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
@Validated
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody EmployeeDto employeeDto) {
        log.info("POST: /employee - {}", employeeDto.toString());
        return ResponseEntity.ok(employeeService.create(employeeDto));
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(@Valid @RequestBody EmployeeDto employeeDto) {
        log.info("PUT: /employee - {}", employeeDto.toString());
        return ResponseEntity.ok(employeeService.update(employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id) {
        log.info("DELETE: /employee - {}", id);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAll(
            @QuerydslPredicate(root = Employee.class) Predicate predicate,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("GET: /employee");
        return ResponseEntity.ok(employeeService.getAll(predicate, sort, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
        log.info("GET: /employee - {}", id);
        return ResponseEntity.ok(employeeService.getById(id));
    }
}
