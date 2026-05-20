package com.bankingsystem.service;

import com.bankingsystem.entity.AuditLog;

import java.util.List;

public interface AuditService {

    void saveAuditLog(
            String action,
            String username,
            String status,
            String description);

    List<AuditLog> getAllLogs();

    List<AuditLog> getLogsByUsername(
            String username);
}