package com.rohan.demo_api.service;

import com.rohan.demo_api.dto.AddEmployeeRequestDto;
import com.rohan.demo_api.dto.EmployeeDto;
import com.rohan.demo_api.entity.EmployeeEntity;
import com.rohan.demo_api.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
// IMP -> Hover over this annotation to see its description.
// No need to manually make a constructor for DI of employeeRepository.
// Making this final and adding this annotation does that for you.
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDto> getAllEmployees() {
//        var employees = employeeRepository.findAll(); // var is same as auto in c++
//        var employeesDtoList = employees
//                .stream()
//                .map(emp -> new EmployeeDto(emp.getId(), emp.getName(), emp.getSalary()))
//                .toList();
//        return employeesDtoList;
        return employeeRepository
                .findAll()
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeDto.class))
                .toList();
        // Doing like this by leveraging the adv of Model mapper, simplifies the code.
    }

    public EmployeeDto getEmployeeById(String id) {
//        var employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
//         findById() returns optional class.
        var employee = employeeRepository.findById(id).orElse(null);
        return employee != null ? modelMapper.map(employee, EmployeeDto.class) : null;
    }

    public EmployeeDto addEmployee(AddEmployeeRequestDto employeeDto) {
        var newEmployee = modelMapper.map(employeeDto, EmployeeEntity.class);
        var addedEmployee = employeeRepository.save(newEmployee);
        return modelMapper.map(addedEmployee, EmployeeDto.class);
    }

    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee with id " + id + " does not exist");
        }
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(String id, Map<String, Object> updates) {
        var employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + id + " does not exist"));
        updates.forEach((key, value) -> {
            var field = ReflectionUtils.findField(EmployeeEntity.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, employee, value);
            }
            else throw new IllegalArgumentException("Invalid field passed for update");
        });
        // This is the standard way of handling patch requests. The whole thing is pretty self-explanatory. Hover Over
        // each function to view their details.
        var updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }
}
