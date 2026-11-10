package com.rohan.demo_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddEmployeeRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name should be of length 2 to 50 characters")
    @Pattern(regexp = "[^0-9]*", message = "Name must not contain numbers")
    private String name;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    private int salary;
}
