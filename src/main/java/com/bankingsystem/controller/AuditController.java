package com.bankingsystem.controller;

import com.bankingsystem.entity.AuditLog;
import com.bankingsystem.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

  
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {

        List<AuditLog> logs =
                auditService.getAllLogs();

        return ResponseEntity.ok(logs);
    }

   
    @GetMapping("/{username}")
    public ResponseEntity<List<AuditLog>>
    getLogsByUsername(
            @PathVariable String username) {

        List<AuditLog> logs =
                auditService.getLogsByUsername(username);

        return ResponseEntity.ok(logs);
    }
}