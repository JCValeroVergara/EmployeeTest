package jcv.employee_test.service;

import jcv.employee_test.models.Employees;
import jcv.employee_test.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSOAPService implements IEmployeeSOAPService {

    @Autowired
    public EmployeesRepository employees;

    @Override
    public void addEmployee(Employees employee) {
        employees.save(employee);
    }
}

