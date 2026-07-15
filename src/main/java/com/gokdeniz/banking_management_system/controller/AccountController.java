package com.gokdeniz.banking_management_system.controller;

import com.gokdeniz.banking_management_system.dto.AccountRequest;
import com.gokdeniz.banking_management_system.entity.Account;
import com.gokdeniz.banking_management_system.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.gokdeniz.banking_management_system.dto.DepositRequest;
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }
    @GetMapping

    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();

    }
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id,
                           @RequestBody DepositRequest request) {

        return accountService.deposit(id, request.getAmount());
    }
}