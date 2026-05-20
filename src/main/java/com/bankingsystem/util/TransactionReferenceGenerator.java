package com.bankingsystem.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionReferenceGenerator {

    private static final SecureRandom random =
            new SecureRandom();

    private static final String PREFIX = "TXN";

    public String generateTransactionReference() {

        
        String timestamp =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyyMMddHHmmss"));

       
        int randomNumber =
                1000 + random.nextInt(9000);

        return PREFIX
                + "-"
                + timestamp
                + "-"
                + randomNumber;
    }
}