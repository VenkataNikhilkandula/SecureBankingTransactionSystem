package com.bankingsystem.service.impl;

import com.bankingsystem.dto.request.TransferRequest;
import com.bankingsystem.dto.response.TransactionResponse;
import com.bankingsystem.entity.Account;
import com.bankingsystem.entity.Transaction;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.TransactionRepository;
import com.bankingsystem.service.AuditService;
import com.bankingsystem.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl
        implements TransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final AuditService auditService;

    @Override
    @Transactional
    public void transferMoney(TransferRequest request) {

        Account sender = accountRepository
                .findWithLockingByAccountNumber(
                        request.getSenderAccount())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sender account not found"));

        Account receiver = accountRepository
                .findWithLockingByAccountNumber(
                        request.getReceiverAccount())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Receiver account not found"));

        if (sender.getBalance()
                .compareTo(request.getAmount()) < 0) {

            throw new RuntimeException(
                    "Insufficient balance");
        }

        sender.setBalance(
                sender.getBalance()
                        .subtract(request.getAmount()));

        receiver.setBalance(
                receiver.getBalance()
                        .add(request.getAmount()));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = Transaction
                .builder()
                .transactionReference(
                        UUID.randomUUID()
                                .toString())
                .senderAccount(
                        request.getSenderAccount())
                .receiverAccount(
                        request.getReceiverAccount())
                .amount(request.getAmount())
                .transactionType("TRANSFER")
                .status("SUCCESS")
                .remarks("Money transferred")
                .build();

        transactionRepository.save(transaction);

        auditService.saveAuditLog(
                "TRANSFER",
                sender.getAccountHolderName(),
                "SUCCESS",
                "Money transferred successfully");
    }

    @Override
    @Transactional
    public void deposit(String accountNumber,
                        Double amount) {

        Account account = accountRepository
                .findWithLockingByAccountNumber(
                        accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"));

        account.setBalance(
                account.getBalance()
                        .add(BigDecimal.valueOf(amount)));

        accountRepository.save(account);

        Transaction transaction = Transaction
                .builder()
                .transactionReference(
                        UUID.randomUUID().toString())
                .senderAccount(accountNumber)
                .receiverAccount(accountNumber)
                .amount(BigDecimal.valueOf(amount))
                .transactionType("DEPOSIT")
                .status("SUCCESS")
                .remarks("Amount deposited")
                .build();

        transactionRepository.save(transaction);

        auditService.saveAuditLog(
                "DEPOSIT",
                account.getAccountHolderName(),
                "SUCCESS",
                "Amount deposited");
    }

    @Override
    @Transactional
    public void withdraw(String accountNumber,
                         Double amount) {

        Account account = accountRepository
                .findWithLockingByAccountNumber(
                        accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"));

        BigDecimal withdrawAmount =
                BigDecimal.valueOf(amount);

        if (account.getBalance()
                .compareTo(withdrawAmount) < 0) {

            throw new RuntimeException(
                    "Insufficient balance");
        }

        account.setBalance(
                account.getBalance()
                        .subtract(withdrawAmount));

        accountRepository.save(account);

        Transaction transaction = Transaction
                .builder()
                .transactionReference(
                        UUID.randomUUID().toString())
                .senderAccount(accountNumber)
                .receiverAccount(accountNumber)
                .amount(withdrawAmount)
                .transactionType("WITHDRAW")
                .status("SUCCESS")
                .remarks("Amount withdrawn")
                .build();

        transactionRepository.save(transaction);

        auditService.saveAuditLog(
                "WITHDRAW",
                account.getAccountHolderName(),
                "SUCCESS",
                "Amount withdrawn");
    }

    @Override
    public List<TransactionResponse>
    getTransactionHistory(
            String accountNumber) {

        List<Transaction> transactions =
                transactionRepository
                        .findBySenderAccountOrReceiverAccount(
                                accountNumber,
                                accountNumber);

        return transactions.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TransactionResponse mapToResponse(
            Transaction transaction) {

        return new TransactionResponse(
                transaction.getId(),
                transaction.getTransactionReference(),
                transaction.getSenderAccount(),
                transaction.getReceiverAccount(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getStatus(),
                transaction.getTransactionDate());
    }
}