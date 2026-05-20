package com.bankingsystem.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class AccountNumberGenerator {

    private static final SecureRandom random =
            new SecureRandom();

    
    public String generateAccountNumber() {

        StringBuilder accountNumber =
                new StringBuilder();

       
        accountNumber.append(
                random.nextInt(9) + 1);

        
        for (int i = 0; i < 11; i++) {

            accountNumber.append(
                    random.nextInt(10));
        }

        return accountNumber.toString();
    }
}