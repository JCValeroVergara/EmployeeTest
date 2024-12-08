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
    private static final String NAMESPACE_URI = "http://www.jcv.com/addEmployee";
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceSoap.class);


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        LOGGER.info("Servicio SOAP llamado para agregar empleado. Datos recibidos: {}", request);
        AddEmployeeResponse response = new AddEmployeeResponse();
        response.setSuccess(true);
        response.setMessage("Employee added: " + request.getFirstName() + " " + request.getLastName());
        return response;
    }
}
