package com.manichand.bankingsystem.service;

import com.manichand.bankingsystem.entity.Account;
import com.manichand.bankingsystem.entity.Transaction;
import com.manichand.bankingsystem.exception.ResourceNotFoundException;
import com.manichand.bankingsystem.repository.AccountRepository;
import com.manichand.bankingsystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(String accountHolderName, BigDecimal initialBalance) {

        Account account = Account.builder()
                .accountNumber(UUID.randomUUID().toString())
                .accountHolderName(accountHolderName)
                .balance(initialBalance)
                .createdAt(LocalDateTime.now())
                .build();

        return accountRepository.save(account);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    public Account deposit(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .accountNumber(accountNumber)
                .type("DEPOSIT")
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return account;
    }

    public Account withdraw(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .accountNumber(accountNumber)
                .type("WITHDRAW")
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return account;
    }

    public List<Transaction> getTransactions(String accountNumber) {

        // Check if account exists
        accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return transactionRepository.findByAccountNumber(accountNumber);
    }
}