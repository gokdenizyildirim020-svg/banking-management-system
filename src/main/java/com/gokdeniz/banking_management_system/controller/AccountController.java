package com.gokdeniz.banking_management_system.controller;

import com.gokdeniz.banking_management_system.dto.AccountRequest;
import com.gokdeniz.banking_management_system.entity.Account;
import com.gokdeniz.banking_management_system.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.gokdeniz.banking_management_system.dto.DepositRequest;
import com.gokdeniz.banking_management_system.dto.WithdrawRequest;
import com.gokdeniz.banking_management_system.dto.TransferRequest;
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
    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id,
                            @RequestBody WithdrawRequest request) {

        return accountService.withdraw(id, request.getAmount());
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {

        accountService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );

        return "Transfer completed successfully";
    }




}