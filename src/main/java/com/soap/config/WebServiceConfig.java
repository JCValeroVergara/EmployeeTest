package com.soap.config;


import com.soap.service.EmployeeServiceSoap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

//    @Override
//    public void addInterceptors(List<EndpointInterceptor> interceptors) {
//        super.addInterceptors(interceptors);
//    }
    @Bean(name = "employees")
    public DefaultWsdl11Definition defaultWsdl11Definition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("EmployeePortType");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://jcvalerovergara.github.io/employee.wsdl");
        return wsdl11Definition;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet() {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public ServletRegistrationBean<HttpServlet> wsldServelet(){

        HttpServlet wsdlServlet = new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                try (InputStream inputStream = new ClassPathResource("employee.wsdl").getInputStream();
                     OutputStream outputStream = resp.getOutputStream()) {
                    resp.setContentType("application/xml");
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                }
            }
        };
        return new ServletRegistrationBean<>(wsdlServlet, "/ws/employee.wsdl");
    }


    @Bean
    public EmployeeServiceSoap employeeServiceSoap() {
        return new EmployeeServiceSoap();
    }
}
