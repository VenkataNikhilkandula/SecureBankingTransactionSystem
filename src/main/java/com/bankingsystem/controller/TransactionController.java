package com.bankingsystem.controller;

import com.bankingsystem.dto.request.TransferRequest;
import com.bankingsystem.dto.response.ApiResponse;
import com.bankingsystem.dto.response.TransactionResponse;
import com.bankingsystem.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

  
    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transferMoney(
            @Valid @RequestBody TransferRequest request) {

        transactionService.transferMoney(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(
                        "Money transferred successfully"));
    }

  
    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>>
    getTransactionHistory(
            @PathVariable String accountNumber) {

        List<TransactionResponse> responses =
                transactionService
                        .getTransactionHistory(accountNumber);

        return ResponseEntity.ok(responses);
    }

    
    @PostMapping("/deposit/{accountNumber}/{amount}")
    public ResponseEntity<ApiResponse> depositMoney(
            @PathVariable String accountNumber,
            @PathVariable Double amount) {

        transactionService.deposit(accountNumber, amount);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Amount deposited successfully"));
    }

    
    @PostMapping("/withdraw/{accountNumber}/{amount}")
    public ResponseEntity<ApiResponse> withdrawMoney(
            @PathVariable String accountNumber,
            @PathVariable Double amount) {

        transactionService.withdraw(accountNumber, amount);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Amount withdrawn successfully"));
    }
}