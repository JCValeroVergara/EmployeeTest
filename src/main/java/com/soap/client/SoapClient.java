package com.soap.client;



import com.soap.wsdl.AddEmployeeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapClient extends WebServiceGatewaySupport {

    public AddEmployeeResponse addEmployeeResponse(AddEmployeeResponse request) {

        SoapActionCallback soapActionCallback = new SoapActionCallback("http://jcvalerovergara.github.io/employee.wsdl/addEmployee");

        return (AddEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws", request, soapActionCallback);

    }
}
