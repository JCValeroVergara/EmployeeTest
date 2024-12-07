package com.example.employee_test.service;


import com.example.employee_test.models.Employees;
import com.example.employee_test.models.ExternalApiResponse;
import com.example.employee_test.models.ExternalEmployee;
import com.example.employee_test.repository.EmployeesRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;


@Service
public class EmployeeService implements IEmployeeService {

    private final RestTemplate restTemplate;
    private final EmployeeProcessor employeeProcessor = new EmployeeProcessor();

    //Injecting the URL of the external API
    @Value("${random.api.url}")
    private String apiUrl;

    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public EmployeesRepository EmployeesRepository;

    @Override
    public List<Employees> getAll() {
        List<Employees> employees = EmployeesRepository.findAll();

        return employees;
    }

    /**
     * Method to call API RandomUser and get a random employee
     * @return Employee object
     */

    public Employees getEmployeeFromAPI() {
        try {
            // Call to the external API
            ExternalApiResponse response = restTemplate.getForObject(apiUrl, ExternalApiResponse.class);

            if (response == null || response.getResults() == null || response.getResults().length == 0) {
                throw new IllegalArgumentException("The API response is null or empty.");
            }

            // Mapping the external employee to the internal employee
            ExternalEmployee externalEmployee = response.getResults()[0];
            Employees employee = mapExternalEmployeeToEmployees(externalEmployee);

            // Validate the employee
            validateEmployee(employee);

            // Processing the employee
            return employeeProcessor.processEmployee(employee);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Error calling the API: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error processing the employee: " + e.getMessage(), e);
        }
    }

    private Employees mapExternalEmployeeToEmployees(ExternalEmployee externalEmployee) {
        Employees employee = new Employees();
        employee.setName(externalEmployee.getFirstName());
        employee.setLastName(externalEmployee.getLastName());
        employee.setDocumentType(externalEmployee.getDocumentType());
        employee.setDocumentNumber(String.valueOf(externalEmployee.getDocumentNumber()));
        employee.setDateBirth(LocalDate.parse(externalEmployee.getDateBirth()));
        employee.setDateJoining(LocalDate.parse(externalEmployee.getDateJoining()));
        employee.setPosition(externalEmployee.getPosition());
        employee.setSalary(externalEmployee.getSalary());
        return employee;
    }

    private String calculateAge(LocalDate dateOfBirth) {
        if(dateOfBirth == null) {
            return "Unknown";
        }
        // Calculate the age string(years, months, days)
        Period age = Period.between(dateOfBirth, LocalDate.now());
        return String.format("%d years, %d months, %d days", age.getYears(), age.getMonths(), age.getDays());
    }

    private String companyDuration(LocalDate startDate) {
        if(startDate == null) {
            return "Unknown";
        }
        // Calculate the company duration string(years, months, days)
        Period companyDuration = Period.between(startDate, LocalDate.now());
        return String.format("%d years, %d months, %d days", companyDuration.getYears(), companyDuration.getMonths(), companyDuration.getDays());
    }

    private void validateEmployee(Employees employee) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Employees>> violations = validator.validate(employee);

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                    .orElse("Unknown validation error");
            throw new IllegalArgumentException("The employee is not valid: " + errorMessage);
        }
    }
}
