package jcv.employee_test.service;

import jcv.employee_test.dto.EmployeeCreateDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class EmployeeProcessor {

    public EmployeeCreateDto processEmployee(EmployeeCreateDto employee) {

        Period age = Period.between(employee.getDateBirth(), LocalDate.now());
        employee.setAge(String.format("%d years, %d months, %d days", age.getYears(), age.getMonths(), age.getDays()));

        Period companyDuration = Period.between(employee.getDateJoining(), LocalDate.now());
        employee.setCompanyDuration(String.format("%d years, %d months, %d days", companyDuration.getYears(), companyDuration.getMonths(), companyDuration.getDays()));

        return employee;
    }
}
