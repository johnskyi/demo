package com.example.demo.data.dto;

import com.example.demo.data.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorthDto {
    private long id;
    @NotBlank
    private String category;
    @NotBlank
    private String name;
    @PastOrPresent
    private LocalDate date;
    @NotNull
    @Positive
    private long price;
    @NotNull
    private Employee employee;
}
