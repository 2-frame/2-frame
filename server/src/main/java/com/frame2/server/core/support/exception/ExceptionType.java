package com.frame2.server.core.support.exception;

import static com.frame2.server.core.support.exception.ExceptionCode.E500;
import static org.springframework.boot.logging.LogLevel.ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {

    DUPLICATE_MEMBER_ERROR(HttpStatus.BAD_REQUEST, ExceptionCode.A01, "중복된 이메일로 회원가입했습니다.", LogLevel.INFO),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, ExceptionCode.A02, "회원이 존재하지 않습니다.", LogLevel.INFO),
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionCode.E500, "알 수 없는 이유로 서버에서 요청을 처리할 수 없습니다.",
            LogLevel.ERROR);

    private final HttpStatus status;

    private final ExceptionCode code;

    private final String message;

    private final LogLevel logLevel;


    ExceptionType(ExceptionCode code, String message) {
        this.status = HttpStatus.BAD_REQUEST;
        this.code = code;
        this.message = message;
        this.logLevel = LogLevel.INFO;
    }

    ExceptionType(HttpStatus status, ExceptionCode code, String message, LogLevel logLevel) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.logLevel = logLevel;
    }
}
