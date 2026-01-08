package org.example.incidentmanagement.service;

import org.example.incidentmanagement.entity.AuditLog;
import org.example.incidentmanagement.repository.AuditLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Async
    public void logRequest(AuditLog auditLog) {
        auditLog.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        auditLogRepository.save(auditLog);
    }
}
