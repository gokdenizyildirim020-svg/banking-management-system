package com.gokdeniz.banking_management_system.repository;

import com.gokdeniz.banking_management_system.entity.Account;
import com.gokdeniz.banking_management_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer(Customer customer);

}