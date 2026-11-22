package com.rohan.demo_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskDto {
    @NotBlank(message = "name of the task can't be empty")
    private String name;
}
