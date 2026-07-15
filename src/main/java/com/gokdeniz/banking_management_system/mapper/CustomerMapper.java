package com.gokdeniz.banking_management_system.mapper;

import com.gokdeniz.banking_management_system.dto.CustomerRequest;
import com.gokdeniz.banking_management_system.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest request) {

        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());

        return customer;
    }
}