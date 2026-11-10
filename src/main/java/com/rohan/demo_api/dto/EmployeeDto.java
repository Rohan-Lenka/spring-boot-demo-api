package com.rohan.demo_api.dto;

import lombok.Data;

@Data
// this comes from lombok lib.
// lombok is used to reduce repetitive boilerplate code like getters, setters, constructors, etc.
// It automatically creates them at compile time.
public class EmployeeDto {
    private String id;
    private String name;
    private String email;
    private int salary;
}
