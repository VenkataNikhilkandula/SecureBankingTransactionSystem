package com.bankingsystem.repository;

import com.bankingsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderAccount(
            String senderAccount);

    List<Transaction> findByReceiverAccount(
            String receiverAccount);

    List<Transaction>
    findBySenderAccountOrReceiverAccount(
            String senderAccount,
            String receiverAccount);

    Transaction findByTransactionReference(
            String transactionReference);
}