package com.example.employee_test.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExternalApiResponse {
    @JsonProperty("results")
    private ExternalEmployee[] results;

}
