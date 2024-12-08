package jcv.employee_test.service;


import jcv.employee_test.dto.EmployeeCreateDto;
import jcv.employee_test.models.Employees;
import jcv.employee_test.models.ExternalApiResponse;
import jcv.employee_test.models.ExternalEmployee;
import jcv.employee_test.repository.EmployeesRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


@Service
public class EmployeeService implements IEmployeeService {

    private final RestTemplate restTemplate;
    private final EmployeeProcessor employeeProcessor = new EmployeeProcessor();



    //Injecting the URL of the external API
    @Value("${api.external.url}")
    private String apiUrl;

    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public EmployeesRepository EmployeesRepository;

    @Autowired
    private EmployeeSOAPService employeeSOAPService;

    @Override
    public List<Employees> getAll() {
        return EmployeesRepository.findAll();
    }

    /**
     * Method to call API RandomUser and get a random employee
     *
     * @return Employee object
     */

    public EmployeeCreateDto getEmployeeFromAPI() {

        try {
            // Call to the external API
            ExternalApiResponse response = restTemplate.getForObject(apiUrl, ExternalApiResponse.class);

            if (response == null || response.getResults() == null || response.getResults().length == 0) {
                throw new IllegalArgumentException("The API response is null or empty.");
            }

            // Mapping the external employee to the internal employee
            ExternalEmployee externalEmployee = response.getResults()[0];
            EmployeeCreateDto employee = mapExternalEmployeeToEmployees(externalEmployee);

            // Calculate the age and company duration
            employee.setAge(calculateAge(employee.getDateBirth()));
            employee.setCompanyDuration(companyDuration(employee.getDateJoining()));

            // Validate the employee
            validateEmployee(employee);

            // transforming the DTO to the entity
            Employees employeeEntity = mapDtoToEmployee(employee);

            //Call the EmployeeServiceSOAP to save the employee
            employeeSOAPService.addEmployee(employeeEntity);

            // Processing the employee
            return employeeProcessor.processEmployee(employee);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Error calling the API: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error processing the employee: " + e.getMessage(), e);
        }
    }

    /**
     * mapping the external employee to the internal employee
     * @param externalEmployee the employee from the external API
     * @return the Employee mapped to the internal model
     */
    private EmployeeCreateDto mapExternalEmployeeToEmployees(ExternalEmployee externalEmployee) {
        EmployeeCreateDto employee = new EmployeeCreateDto();

        // assigning the attributes
        employee.setFirstName(externalEmployee.getFirstName());
        employee.setLastName(externalEmployee.getLastName());
        employee.setDocumentType(externalEmployee.getDocumentType());
        employee.setDocumentNumber(String.valueOf(externalEmployee.getDocumentNumber()));

        // mapping the dates of birth and joining
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (externalEmployee.getDateBirth() != null) {
            employee.setDateBirth(LocalDate.parse(externalEmployee.getDateBirth(), formatter));
        }

        if (externalEmployee.getDateJoining() != null) {
            employee.setDateJoining(LocalDate.parse(externalEmployee.getDateJoining(), formatter));
        }

        // assigning the position and salary
        employee.setPosition(externalEmployee.getPosition());
        employee.setSalary(externalEmployee.getSalary());

        return employee;
    }

    /**
     * Method to save an employee
     * @return the saved employee
     */
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

    /**
     * Method to mapDto to Employee
     */
    private Employees mapDtoToEmployee(EmployeeCreateDto employee) {
        Employees employeeEntity = new Employees();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setDocumentNumber(employee.getDocumentNumber());
        employeeEntity.setDocumentType(employee.getDocumentType());
        employeeEntity.setDateBirth(employee.getDateBirth());
        employeeEntity.setDateJoining(employee.getDateJoining());
        employeeEntity.setPosition(employee.getPosition());
        employeeEntity.setSalary(employee.getSalary());
        employeeEntity.setAge(employee.getAge());
        employeeEntity.setCompanyDuration(employee.getCompanyDuration());
        return employeeEntity;
    }

    private void validateEmployee(EmployeeCreateDto employee) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<EmployeeCreateDto>> violations = validator.validate(employee);

            if (!violations.isEmpty()) {
                String errorMessage = violations.stream()
                        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                        .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                        .orElse("Unknown validation error");
                throw new IllegalArgumentException("The employee is not valid: " + errorMessage);
            }
        }
    }
}
