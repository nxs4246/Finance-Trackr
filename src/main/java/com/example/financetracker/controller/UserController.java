package com.example.financetracker.controller;

import com.example.financetracker.dto.LoginRequest;
import com.example.financetracker.dto.SignupRequest;
import com.example.financetracker.model.User;
import com.example.financetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Signup Endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        if (userService.isUsernameTaken(signupRequest.getUsername()) ||
                userService.isEmailTaken(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Username or email is already taken");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());

        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (userService.validateCredentials(loginRequest.getUsername(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}