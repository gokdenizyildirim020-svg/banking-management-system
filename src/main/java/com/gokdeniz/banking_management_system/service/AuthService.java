package com.gokdeniz.banking_management_system.service;

import com.gokdeniz.banking_management_system.dto.LoginRequest;
import com.gokdeniz.banking_management_system.dto.LoginResponse;
import com.gokdeniz.banking_management_system.entity.Customer;
import com.gokdeniz.banking_management_system.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gokdeniz.banking_management_system.exception.InvalidCredentialsException;
import com.gokdeniz.banking_management_system.security.JwtService;
import com.gokdeniz.banking_management_system.dto.LoginResponse;
@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(CustomerRepository customerRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(),
                customer.getPassword())) {

            throw new InvalidCredentialsException("Invalid password");
        }

        String token = jwtService.generateToken(customer.getEmail());

        return new LoginResponse(token);
    }
}