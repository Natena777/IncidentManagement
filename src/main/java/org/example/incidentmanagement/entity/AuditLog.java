package org.example.incidentmanagement.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "sys_audit_log")
public class AuditLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    @Column(name = "endpoint", nullable = false, length = 255)
    private String endpoint;

    @Column(name = "http_method", nullable = false, length = 10)
    private String httpMethod;

    @Column(name = "success", nullable = false)
    private Boolean success;

    @Column(name = "http_status")
    private Integer httpStatus;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "error_message", length = 500)
    private String errorMessage;


    @Column(name = "duration_ms")
    private Integer durationMs;

    @Column(name = "client_ip", length = 50)
    private String clientIp;

    @Column(name = "trace_id", length = 100)
    private String traceId;

    public AuditLog() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
