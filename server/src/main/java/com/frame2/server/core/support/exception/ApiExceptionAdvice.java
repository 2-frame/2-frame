package com.frame2.server.core.support.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ExceptionResponse> handleCoreException(DomainException e) {
        switch (e.getExceptionType().getLogLevel()) {
            case ERROR -> log.error("domainException : {}", e.getMessage(), e);
            case WARN -> log.warn("domainException : {}", e.getMessage(), e);
            default -> log.info("domainException: {}", e.getMessage(), e);
        }
        return ResponseEntity.status(e.getExceptionType().getStatus())
                .body(ExceptionResponse.from(e.getExceptionType(), e.getData()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage(), e);
        return ResponseEntity.status(500).body(ExceptionResponse.from(ExceptionType.DEFAULT_ERROR));
    }

}
