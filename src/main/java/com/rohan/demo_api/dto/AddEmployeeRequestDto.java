package com.rohan.demo_api.dto;

import jakarta.validation.constraints.*;
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

    @Positive(message = "Salary can't be negative or missing")
    // Can't use @NotBlank here coz it is for strings. Another solution -> make salary an Integer and add @NotNull.
    private int salary;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "City is required")
    private String city;
}
