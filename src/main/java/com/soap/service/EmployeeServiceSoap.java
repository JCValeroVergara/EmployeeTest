package com.soap.service;

import com.soap.wsdl.AddEmployeeRequest;
import com.soap.wsdl.AddEmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeServiceSoap {
    private static final String NAMESPACE_URI = "https://jcvalerovergara.github.io/employee.wsdl";
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceSoap.class);


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        LOGGER.info("SOAP service called to add employee. Data received: {}", request);


        AddEmployeeResponse response = new AddEmployeeResponse();
        response.setMessage(request.getFirstName() + " has been successfully added");

        LOGGER.info("Service response SOAP: {}", response);
        return response;
    }
}
