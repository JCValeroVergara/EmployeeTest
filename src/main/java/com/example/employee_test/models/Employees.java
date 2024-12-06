package com.example.employee_test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String name;
    private String lastName;
    private String documentNumber;
    private String documentType;
    private LocalDate dateBirth;
    private LocalDate dateJoining;
    private String position;
    private Double salary;
    private String age;
    private String companyDuration;
}
