package com.example.employee_test.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExternalApiResponse {
    @JsonProperty("results")
    private ExternalEmployee[] results;

    @JsonProperty("info")
    private ApiInfo info;

    // Getters and setters

    public ExternalEmployee[] getResults() {
        return results;
    }

    public void setResults(ExternalEmployee[] results) {
        this.results = results;
    }

    public ApiInfo getInfo() {
        return info;
    }

    public void setInfo(ApiInfo info) {
        this.info = info;
    }
}

class ApiInfo {
    // Define the structure of the "info" field as per the API response
    private String seed;
    private int results;
    private int page;
    private String version;

}
