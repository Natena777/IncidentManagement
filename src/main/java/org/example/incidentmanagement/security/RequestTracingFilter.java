package org.example.incidentmanagement.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class RequestTracingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestTracingFilter.class);
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // TraceID გენერირება
        String traceId = UUID.randomUUID().toString();

        // MDC-ში ჩაწერა
        MDC.put(TRACE_ID, traceId);

        // Response header-ში დამატება
        httpResponse.setHeader("X-Trace-Id", traceId);

        // დროის ფიქსირება
        long startTime = System.currentTimeMillis();

        // TRACE დაწყების ლოგი
        log.info(">>> TRACE STARTED - Method: {}, URI: {}, Client IP: {}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                getClientIP(httpRequest));

        try {
            chain.doFilter(request, response);
        } finally {
            // დროის გამოთვლა
            long duration = System.currentTimeMillis() - startTime;

            // TRACE დასრულების ლოგი
            log.info("<<< TRACE COMPLETED - Status: {}, Duration: {}ms",
                    httpResponse.getStatus(),
                    duration);

            // გასუფთავება
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
}
