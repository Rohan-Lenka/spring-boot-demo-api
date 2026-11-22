package com.rohan.demo_api.service;

import com.rohan.demo_api.dto.AddTaskDto;
import com.rohan.demo_api.dto.TaskDto;
import com.rohan.demo_api.entity.Task;
import com.rohan.demo_api.repository.EmployeeRepository;
import com.rohan.demo_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public TaskDto addTask(String empId, AddTaskDto addtaskDto) {
        var employee = employeeRepository
                .findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + empId + " does not exist"));
        var newTask = Task
                .builder()
                .name(addtaskDto.getName())
                .employee(employee)
                .build();
        taskRepository.save(newTask);
        return modelMapper.map(newTask, TaskDto.class);
    }
}
