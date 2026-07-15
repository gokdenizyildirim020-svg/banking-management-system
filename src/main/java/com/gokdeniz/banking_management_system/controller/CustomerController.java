package com.gokdeniz.banking_management_system.controller;

import com.gokdeniz.banking_management_system.dto.CustomerRequest;
import com.gokdeniz.banking_management_system.entity.Customer;
import com.gokdeniz.banking_management_system.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody CustomerRequest request) {

        return customerService.createCustomer(request);

    }
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}