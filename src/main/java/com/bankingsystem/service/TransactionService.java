package com.bankingsystem.service;

import com.bankingsystem.dto.request.TransferRequest;
import com.bankingsystem.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    void transferMoney(TransferRequest request);

    void deposit(String accountNumber,
                 Double amount);

    void withdraw(String accountNumber,
                  Double amount);

    List<TransactionResponse> getTransactionHistory(
            String accountNumber);
}