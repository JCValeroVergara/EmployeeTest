package com.soap.client;

import com.soap.wsdl.AddEmployeeRequest;
import com.soap.wsdl.AddEmployeeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapClient extends WebServiceGatewaySupport {

    public AddEmployeeResponse getAddResponse(AddEmployeeRequest request) {

        SoapActionCallback soapActionCallback = new SoapActionCallback("http://www.jcv.com/addEmployee");

        AddEmployeeResponse response = (AddEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws/employees", request, soapActionCallback);

        return response;
    }
}
