package com.example.employee_test.controllers;

import com.example.employee_test.dto.EmployeeCreateDto;
import com.example.employee_test.models.Employees;
import com.example.employee_test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /**
     * Endpoint to fetch an employee from an external API and process it
     *
     * @return Processed Employee object
     */
    @GetMapping("/process")
    public ResponseEntity<EmployeeCreateDto> getEmployeeFromAPI() {
        EmployeeCreateDto employee = employeeService.getEmployeeFromAPI();
        return ResponseEntity.ok(employee);
    }

    /**
     * Endpoint to fetch all employees from the database
     *
     * @return List of Employees
     */

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }
}
