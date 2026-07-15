package com.gokdeniz.banking_management_system.controller;

import com.gokdeniz.banking_management_system.dto.LoginRequest;
import com.gokdeniz.banking_management_system.dto.LoginResponse;
import com.gokdeniz.banking_management_system.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
