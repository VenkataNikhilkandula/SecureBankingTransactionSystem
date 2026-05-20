package com.bankingsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Long id;

    private String transactionReference;

    private String senderAccount;

    private String receiverAccount;

    private BigDecimal amount;

    private String transactionType;

    private String status;

    private LocalDateTime transactionDate;
}