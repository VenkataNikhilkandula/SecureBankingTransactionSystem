package com.bankingsystem.service;

import com.bankingsystem.dto.request.AccountCreateRequest;
import com.bankingsystem.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse createAccount(
            AccountCreateRequest request);

    AccountResponse getAccountByNumber(
            String accountNumber);

    String getBalance(String accountNumber);

    void deleteAccount(String accountNumber);
}