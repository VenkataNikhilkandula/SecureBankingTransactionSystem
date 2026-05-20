package com.bankingsystem.service.impl;

import com.bankingsystem.dto.request.AccountCreateRequest;
import com.bankingsystem.dto.response.AccountResponse;
import com.bankingsystem.entity.Account;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.service.AccountService;
import com.bankingsystem.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl
        implements AccountService {

    private final AccountRepository accountRepository;

    private final AuditService auditService;

    @Override
    public AccountResponse createAccount(
            AccountCreateRequest request) {

        String accountNumber =
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 12);

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountHolderName(
                        request.getAccountHolderName())
                .accountType(request.getAccountType())
                .balance(request.getInitialBalance())
                .status("ACTIVE")
                .build();

        Account savedAccount =
                accountRepository.save(account);

        auditService.saveAuditLog(
                "ACCOUNT_CREATED",
                request.getAccountHolderName(),
                "SUCCESS",
                "Bank account created");

        return mapToResponse(savedAccount);
    }

    @Override
    public AccountResponse getAccountByNumber(
            String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"));

        return mapToResponse(account);
    }

    @Override
    public String getBalance(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"));

        return account.getBalance().toString();
    }

    @Override
    public void deleteAccount(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"));

        accountRepository.delete(account);

        auditService.saveAuditLog(
                "ACCOUNT_DELETED",
                account.getAccountHolderName(),
                "SUCCESS",
                "Bank account deleted");
    }

    private AccountResponse mapToResponse(
            Account account) {

        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getAccountType(),
                account.getBalance(),
                account.getStatus(),
                account.getCreatedAt());
    }
}