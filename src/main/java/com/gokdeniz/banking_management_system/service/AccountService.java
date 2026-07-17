package com.gokdeniz.banking_management_system.service;

import com.gokdeniz.banking_management_system.dto.AccountRequest;
import com.gokdeniz.banking_management_system.entity.Account;
import com.gokdeniz.banking_management_system.entity.Customer;
import com.gokdeniz.banking_management_system.exception.AccessDeniedException;
import com.gokdeniz.banking_management_system.repository.AccountRepository;
import com.gokdeniz.banking_management_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.gokdeniz.banking_management_system.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import com.gokdeniz.banking_management_system.exception.InsufficientBalanceException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;

    }
    public Account createAccount(AccountRequest request) {

        Customer customer = getCurrentCustomer();
        Account account = new Account();

        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());

        account.setCustomer(customer);

        return accountRepository.save(account);
    }
    public List<Account> getAllAccounts() {

        Customer currentCustomer = getCurrentCustomer();

        return accountRepository.findByCustomer(currentCustomer);

    }
    public Account deposit(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));
        validateAccountOwner(account);

        account.setBalance(account.getBalance() + amount);

        return accountRepository.save(account);

    }
    public Account withdraw(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

        validateAccountOwner(account);

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        return accountRepository.save(account);
    }
    @Transactional
    public void transfer(Long fromId, Long toId, Double amount) {

        Account fromAccount = accountRepository.findById(fromId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));
        validateAccountOwner(fromAccount);


        Account toAccount = accountRepository.findById(toId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
    private Customer getCurrentCustomer() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (Customer) authentication.getPrincipal();
    }
    private void validateAccountOwner(Account account) {

        Customer currentCustomer = getCurrentCustomer();

        if (!account.getCustomer().getId().equals(currentCustomer.getId())) {
            throw new AccessDeniedException(
                    "You are not allowed to access this account");
        }
    }





}