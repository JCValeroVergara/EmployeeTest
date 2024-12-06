package com.example.employee_test.service;


import com.example.employee_test.models.Employees;
import com.example.employee_test.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    public EmployeesRepository EmployeesRepository;

    @Override
    public List<Employees> getAll() {
        List<Employees> employees = EmployeesRepository.findAll();

        return employees;
    }
}
