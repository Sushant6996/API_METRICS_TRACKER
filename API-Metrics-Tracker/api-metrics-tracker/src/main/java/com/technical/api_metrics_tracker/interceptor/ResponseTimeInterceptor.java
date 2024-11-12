package com.technical.api_metrics_tracker.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ResponseTimeInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "startTime";
    private static final Logger logger = LoggerFactory.getLogger(ResponseTimeInterceptor.class);

    @Override
    public boolean preHandle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception  {
        // Capture the start time before the request is processed
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime); // Save the start time in the request
        return true; // Continue processing the request
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // Retrieve the start time from the request attribute
        Long startTime = (Long) request.getAttribute(START_TIME);

        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Response Time for API: {} = {} ms", request.getRequestURI(), duration);
        } else {
            logger.warn("START_TIME attribute is missing for request: {}", request.getRequestURI());
        }
    }
}
