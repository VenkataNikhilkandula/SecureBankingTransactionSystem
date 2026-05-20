package com.bankingsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long id;

    private String accountNumber;

    private String accountHolderName;

    private String accountType;

    private BigDecimal balance;

    private String status;

    private LocalDateTime createdAt;
}