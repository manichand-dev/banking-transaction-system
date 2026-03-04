package com.manichand.bankingsystem.controller;

import com.manichand.bankingsystem.entity.Account;
import com.manichand.bankingsystem.entity.Transaction;
import com.manichand.bankingsystem.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/create")
    public Account createAccount(@RequestParam String name,
                                 @RequestParam BigDecimal balance) {

        return accountService.createAccount(name, balance);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return accountService.getAccountByNumber(accountNumber);
    }

    @GetMapping("/deposit")
    public Account deposit(@RequestParam String accountNumber,
                           @RequestParam BigDecimal amount) {

        return accountService.deposit(accountNumber, amount);
    }

    @GetMapping("/withdraw")
    public Account withdraw(@RequestParam String accountNumber,
                            @RequestParam BigDecimal amount) {

        return accountService.withdraw(accountNumber, amount);
    }

    @GetMapping("/{accountNumber}/transactions")
    public List<Transaction> getTransactions(@PathVariable String accountNumber) {
        return accountService.getTransactions(accountNumber);
    }
}