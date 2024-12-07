package com.example.employee_test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalEmployee {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("documentNumber")
    private long documentNumber;

    @JsonProperty("dateBirth")
    private String dateBirth;

    @JsonProperty("dateJoining")
    private String dateJoining;

    @JsonProperty("position")
    private String position;

    @JsonProperty("salary")
    private double salary;
}