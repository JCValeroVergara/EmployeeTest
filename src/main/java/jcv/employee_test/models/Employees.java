package jcv.employee_test.models;

import jcv.employee_test.validations.Adult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.*;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The first name is required")
    private String firstName;

    @NotBlank(message = "The last name is required")
    private String lastName;

    @NotBlank(message = "The document number is required")
    private String documentNumber;

    @NotBlank(message = "The document type is required")
    private String documentType;

    @NotNull(message = "The date of joining cannot be null")
    @Adult
    private LocalDate dateBirth;

    @NotNull(message = "The date of joining cannot be null")
    @Past(message = "The date of joining must be in the past")
    private LocalDate dateJoining;

    @NotBlank(message = "The position is required")
    private String position;

    @NotNull(message = "Salary must not be null")
    @Positive(message = "Salary must be positive")
    private Double salary;

    private String age;
    private String companyDuration;
}
