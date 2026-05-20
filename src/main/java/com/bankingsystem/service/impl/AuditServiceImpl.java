package com.bankingsystem.service.impl;

import com.bankingsystem.entity.AuditLog;
import com.bankingsystem.repository.AuditLogRepository;
import com.bankingsystem.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl
        implements AuditService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void saveAuditLog(
            String action,
            String username,
            String status,
            String description) {

        AuditLog log = AuditLog.builder()
                .action(action)
                .username(username)
                .status(status)
                .description(description)
                .ipAddress("127.0.0.1")
                .build();

        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getAllLogs() {

        return auditLogRepository.findAll();
    }

    @Override
    public List<AuditLog> getLogsByUsername(
            String username) {

        return auditLogRepository
                .findByUsername(username);
    }
}