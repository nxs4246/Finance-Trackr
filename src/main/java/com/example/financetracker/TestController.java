package com.example.financetracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Finance Tracker Application!";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, this is another API endpoint!";
    }

}
