package com.frame2.server.core.support.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionCode.E500, "알 수 없는 이유로 서버에서 요청을 처리할 수 없습니다.",
            LogLevel.ERROR);

    private final HttpStatus status;

    private final ExceptionCode code;

    private final String message;

    private final LogLevel logLevel;

}
