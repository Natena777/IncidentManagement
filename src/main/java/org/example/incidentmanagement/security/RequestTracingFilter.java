package org.example.incidentmanagement.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.incidentmanagement.entity.AuditLog;
import org.example.incidentmanagement.service.AuditLogService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@Order(1)
public class RequestTracingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestTracingFilter.class);
    private static final String TRACE_ID = "traceId";

    private final AuditLogService auditLogService;
    private final CurrentUserService currentUserService;

    public RequestTracingFilter(AuditLogService auditLogService,
                                CurrentUserService currentUserService) {
        this.auditLogService = auditLogService;
        this.currentUserService = currentUserService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID, traceId);
        wrappedResponse.setHeader("X-Trace-Id", traceId);

        long startTime = System.currentTimeMillis();

        log.info(">>> TRACE STARTED - Method: {}, URI: {}, Client IP: {}",
                wrappedRequest.getMethod(),
                wrappedRequest.getRequestURI(),
                getClientIP(wrappedRequest));

        try {
            chain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            int status = wrappedResponse.getStatus();
            boolean success = status < 400;


            AuditLog auditLog = new AuditLog();
            auditLog.setTraceId(traceId);
            auditLog.setCreatedBy(currentUserService.getCurrentUserId());
            auditLog.setServiceName("IncidentManagement");
            auditLog.setEndpoint(wrappedRequest.getRequestURI());
            auditLog.setHttpMethod(wrappedRequest.getMethod());
            auditLog.setSuccess(success);
            auditLog.setHttpStatus(status);
            auditLog.setDurationMs((int) duration);
            auditLog.setClientIp(getClientIP(wrappedRequest));


            if (!success) {
                auditLog.setErrorMessage("HTTP Error " + status);
            }

            auditLogService.logRequest(auditLog);

            // აუცილებელია response ხელახლა client-ზე
            wrappedResponse.copyBodyToResponse();

            log.info("<<< TRACE COMPLETED - Status: {}, Duration: {}ms", status, duration);
            MDC.clear();
        }
    }

    private String getClientIP(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] buf = request.getContentAsByteArray();
        if (buf.length > 0) {
            return new String(buf, StandardCharsets.UTF_8);
        }
        return null;
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {
        byte[] buf = response.getContentAsByteArray();
        if (buf.length > 0) {
            return new String(buf, StandardCharsets.UTF_8);
        }
        return null;
    }
}
