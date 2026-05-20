package com.bankingsystem.service.impl;

import com.bankingsystem.dto.request.LoginRequest;
import com.bankingsystem.dto.request.RegisterRequest;
import com.bankingsystem.dto.response.JwtResponse;
import com.bankingsystem.entity.Role;
import com.bankingsystem.entity.User;
import com.bankingsystem.repository.UserRepository;
import com.bankingsystem.security.JwtUtil;
import com.bankingsystem.service.AuditService;
import com.bankingsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final AuditService auditService;

    @Override
    public void registerUser(RegisterRequest request) {

        if (userRepository.existsByUsername(
                request.getUsername())) {

            throw new RuntimeException(
                    "Username already exists");
        }

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(
                        request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .enabled(true)
                .build();

        userRepository.save(user);

        auditService.saveAuditLog(
                "REGISTER",
                request.getUsername(),
                "SUCCESS",
                "User registered successfully");
    }

    @Override
    public JwtResponse loginUser(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(
                user.getUsername());

        auditService.saveAuditLog(
                "LOGIN",
                user.getUsername(),
                "SUCCESS",
                "User logged in successfully");

        return new JwtResponse(
                token,
                user.getUsername(),
                user.getRole().name());
    }
}