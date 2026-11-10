package com.rohan.demo_api.controller;

import com.rohan.demo_api.dto.AddEmployeeRequestDto;
import com.rohan.demo_api.dto.EmployeeDto;
import com.rohan.demo_api.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController  // under the hood, this has the @ResponseBody annotation which means
// it automatically returns JSON responses.
@RequestMapping("/employees")
// this controller handles requests starting from /employees
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

//    @GetMapping("/employee")
//    public EmployeeDto getEmployee() {
//        return new EmployeeDto(1001L, "abc", 50000);
//        // this automatically gets converted to proper JSON response (via jackson lib).
//    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
//        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
        return ResponseEntity.ok(employeeService.getAllEmployees()); // shortcut for status code 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id) {
        // the var passed via the function should be named same as the path param else do like this_
        // public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") String empId) {
        var employee = employeeService.getEmployeeById(id);
        if (employee == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody @Valid AddEmployeeRequestDto employeeDto) {
        // @Valid annotation is for validating the request body.
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
        // .noContent(): This is a static factory method that initializes
        // a ResponseEntity.HeadersBuilder with the status set to HttpStatus.NO_CONTENT (HTTP 204).
        // .build(): This finishes creating the ResponseEntity object without adding a response body.
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, updates));
    }
}
