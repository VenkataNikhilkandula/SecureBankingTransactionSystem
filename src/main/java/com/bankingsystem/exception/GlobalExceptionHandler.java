package com.bankingsystem.exception;

import com.bankingsystem.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>>
    handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("timestamp",
                LocalDateTime.now());

        response.put("status",
                HttpStatus.NOT_FOUND.value());

        response.put("error",
                "NOT_FOUND");

        response.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND);
    }

   
    @ExceptionHandler(
            InsufficientBalanceException.class)
    public ResponseEntity<Map<String, Object>>
    handleInsufficientBalanceException(
            InsufficientBalanceException ex) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("timestamp",
                LocalDateTime.now());

        response.put("status",
                HttpStatus.BAD_REQUEST.value());

        response.put("error",
                "INSUFFICIENT_BALANCE");

        response.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(
            InvalidTransactionException.class)
    public ResponseEntity<Map<String, Object>>
    handleInvalidTransactionException(
            InvalidTransactionException ex) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("timestamp",
                LocalDateTime.now());

        response.put("status",
                HttpStatus.BAD_REQUEST.value());

        response.put("error",
                "INVALID_TRANSACTION");

        response.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>>
    handleUnauthorizedException(
            UnauthorizedException ex) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("timestamp",
                LocalDateTime.now());

        response.put("status",
                HttpStatus.UNAUTHORIZED.value());

        response.put("error",
                "UNAUTHORIZED");

        response.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED);
    }

   
    @ExceptionHandler(
            BadCredentialsException.class)
    public ResponseEntity<ApiResponse>
    handleBadCredentialsException(
            BadCredentialsException ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse(
                        "Invalid username or password"));
    }

    
    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {

                    String fieldName =
                            ((FieldError) error)
                                    .getField();

                    String errorMessage =
                            error.getDefaultMessage();

                    errors.put(
                            fieldName,
                            errorMessage);
                });

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(
            IllegalArgumentException.class)
    public ResponseEntity<ApiResponse>
    handleIllegalArgumentException(
            IllegalArgumentException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(
                        ex.getMessage()));
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
    handleGlobalException(Exception ex) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("timestamp",
                LocalDateTime.now());

        response.put("status",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        response.put("error",
                "INTERNAL_SERVER_ERROR");

        response.put("message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}