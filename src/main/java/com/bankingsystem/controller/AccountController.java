package com.bankingsystem.controller;

import com.bankingsystem.dto.request.AccountCreateRequest;
import com.bankingsystem.dto.response.AccountResponse;
import com.bankingsystem.dto.response.ApiResponse;
import com.bankingsystem.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody AccountCreateRequest request) {

        AccountResponse response =
                accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountByNumber(
            @PathVariable String accountNumber) {

        AccountResponse response =
                accountService.getAccountByNumber(accountNumber);

        return ResponseEntity.ok(response);
    }

    
    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<ApiResponse> getAccountBalance(
            @PathVariable String accountNumber) {

        String balance =
                accountService.getBalance(accountNumber);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Current Balance : " + balance));
    }

    
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse> deleteAccount(
            @PathVariable String accountNumber) {

        accountService.deleteAccount(accountNumber);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Account deleted successfully"));
    }
}