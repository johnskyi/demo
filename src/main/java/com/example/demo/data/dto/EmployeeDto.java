package com.example.demo.data.dto;

import com.example.demo.data.entity.Sex;
import com.example.demo.data.entity.Worth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank
    private String secondName;
    @NotBlank
    private String name;
    @NotBlank
    private String patronymic;
    @NotBlank
    @Email
    private String email;
    private Sex sex;
    @NotEmpty
    @Valid
    private List<Worth> worths = new ArrayList<>();
}
