package jcv.employee_test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/")
    public String check() {
        return "Application is running successful on port 8080";
    }
}
