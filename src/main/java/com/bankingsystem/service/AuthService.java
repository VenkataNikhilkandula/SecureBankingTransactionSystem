package com.bankingsystem.service;

import com.bankingsystem.dto.request.LoginRequest;
import com.bankingsystem.dto.request.RegisterRequest;
import com.bankingsystem.dto.response.JwtResponse;

public interface AuthService {

    void registerUser(RegisterRequest request);

    JwtResponse loginUser(LoginRequest request);
}