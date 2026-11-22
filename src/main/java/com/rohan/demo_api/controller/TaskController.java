package com.rohan.demo_api.controller;

import com.rohan.demo_api.dto.AddTaskDto;
import com.rohan.demo_api.dto.TaskDto;
import com.rohan.demo_api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/{empId}")
    public ResponseEntity<TaskDto> createTask(@PathVariable String empId, @RequestBody @Valid AddTaskDto addTaskDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(empId, addTaskDto));
    }
}
