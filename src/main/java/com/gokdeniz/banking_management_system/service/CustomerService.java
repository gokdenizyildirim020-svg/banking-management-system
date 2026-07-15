package com.gokdeniz.banking_management_system.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.gokdeniz.banking_management_system.dto.CustomerRequest;
import com.gokdeniz.banking_management_system.entity.Customer;
import com.gokdeniz.banking_management_system.mapper.CustomerMapper;
import com.gokdeniz.banking_management_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomerService(CustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder) {

        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer createCustomer(CustomerRequest request) {

        Customer customer = CustomerMapper.toEntity(request);

        customer.setPassword(passwordEncoder.encode(request.getPassword()));

        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();

    }
}