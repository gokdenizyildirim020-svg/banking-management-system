package com.gokdeniz.banking_management_system.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class AccountRequest {

    @NotNull(message = "Customer id is required")

    private Long customerId;

    public Long getCustomerId() {

        return customerId;

    }

    public void setCustomerId(Long customerId) {

        this.customerId = customerId;

    }

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Balance is required")
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;



    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


}