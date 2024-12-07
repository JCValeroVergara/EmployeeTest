package com.example.employee_test.models;

import com.example.employee_test.validations.Adult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "The name is required")
    private Integer id;

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The last name is required")
    private String lastName;

    @NotBlank(message = "The document number is required")
    private String documentNumber;

    @NotBlank(message = "The document type is required")
    private String documentType;

    @NotBlank(message = "The date of birth is required")
    @Adult
    private LocalDate dateBirth;

    @NotBlank(message = "The date of joining is required")
    private LocalDate dateJoining;

    @NotBlank(message = "The position is required")
    private String position;

    @NotBlank(message = "The salary is required")
    private Double salary;

    private String age;
    private String companyDuration;
}
