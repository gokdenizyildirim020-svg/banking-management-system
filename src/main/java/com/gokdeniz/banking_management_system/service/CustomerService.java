package com.gokdeniz.banking_management_system.service;

import com.gokdeniz.banking_management_system.dto.CustomerRequest;
import com.gokdeniz.banking_management_system.entity.Customer;
import com.gokdeniz.banking_management_system.mapper.CustomerMapper;
import com.gokdeniz.banking_management_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerRequest request) {

        Customer customer = CustomerMapper.toEntity(request);

        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();

    }
}