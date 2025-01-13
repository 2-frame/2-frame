package com.frame2.server.global.config;

import com.frame2.server.core.support.exception.DomainException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.logging.LogLevel;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void handleUncaughtException(Throwable e, Method method, Object... params) {
        if (e instanceof DomainException) {
            switch (((DomainException) e).getExceptionType().getLogLevel()) {
                case LogLevel.ERROR -> log.error("CoreException : {}", e.getMessage(), e);
                case LogLevel.WARN -> log.warn("CoreException : {}", e.getMessage(), e);
                default -> log.info("CoreException : {}", e.getMessage(), e);
            }
        }
        else {
            log.error("Exception : {}", e.getMessage(), e);
        }
    }

}
