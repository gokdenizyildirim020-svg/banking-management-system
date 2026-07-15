package com.gokdeniz.banking_management_system.service;

import com.gokdeniz.banking_management_system.dto.AccountRequest;
import com.gokdeniz.banking_management_system.entity.Account;
import com.gokdeniz.banking_management_system.entity.Customer;
import com.gokdeniz.banking_management_system.repository.AccountRepository;
import com.gokdeniz.banking_management_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.gokdeniz.banking_management_system.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }
    public Account createAccount(AccountRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

        Account account = new Account();

        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());

        account.setCustomer(customer);

        return accountRepository.save(account);
    }
    public List<Account> getAllAccounts() {

        return accountRepository.findAll();

    }
    public Account deposit(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)

                .orElseThrow();

        account.setBalance(account.getBalance() + amount);

        return accountRepository.save(account);

    }
    public Account withdraw(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow();

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        return accountRepository.save(account);
    }
    @Transactional
    public void transfer(Long fromId, Long toId, Double amount) {

        Account fromAccount = accountRepository.findById(fromId)
                .orElseThrow();

        Account toAccount = accountRepository.findById(toId)
                .orElseThrow();

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }




}