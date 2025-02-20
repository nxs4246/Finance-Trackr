package com.example.financetracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String getHome() {
        return "Homepage";
    }

    @GetMapping("/app/admin")
    public String getAdmin() {
        return "Admin";
    }

    @GetMapping("/app/dashboard")
    public String getDashboard() {
        return "Dashboard";
    }
}
