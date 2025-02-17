package com.frame2.server.core.support.exception;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

import static com.frame2.server.core.support.exception.ExceptionCode.E500;
import static org.springframework.boot.logging.LogLevel.ERROR;
import static org.springframework.boot.logging.LogLevel.INFO;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public enum ExceptionType {

    SEND_MAIL_FAIL(INTERNAL_SERVER_ERROR, E500, "메일 서버에서 알 수 없는 이유로 요청을 처리할 수 없습니다", ERROR),
    DEFAULT_ERROR(INTERNAL_SERVER_ERROR, E500, "알 수 없는 이유로 서버에서 요청을 처리할 수 없습니다.", ERROR),
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, ExceptionCode.E401, "권한이 없습니다", INFO),
    DUPLICATE_NICKNAME(ExceptionCode.A01, "중복된 닉네임으로 회원가입했습니다."),
    MEMBER_NOT_FOUND(ExceptionCode.A02, "회원이 존재하지 않습니다."),
    DUPLICATE_EMAIL(ExceptionCode.A03, "중복된 이메일로 회원가입했습니다."),
    LOGIN_FAIL(ExceptionCode.A04, "존재하지 않는 아이디 혹은 이메일입니다."),

    ACCOUNT_LOCKED(ExceptionCode.A05, "현재 계정이 잠금 상태입니다"),
    REQUIRE_EMAIL_AUTHENTICATION(ExceptionCode.A06, "이메일 인증 전입니다. 이메일 인증을 해주세요"),
    REQUIRE_UNLOCK_DORMANT(ExceptionCode.A07, "현재 계정이 휴면상태입니다."),

    INVALID_SECRET_CODE(ExceptionCode.A08, "유효하지 않은 인증 코드입니다."),


    // - 상품 -
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.P04, "존재하지 않는 상품입니다.", ERROR),

    // - 상품 QnA -
    PRODUCT_QNA_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.B01, "존재하지 않는 QnA 입니다.", ERROR),

    // - 상품 리뷰 -
    PRODUCT_REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.B02, "존재하지 않는 리뷰 입니다.", ERROR),

    // - 카테고리 -
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.CT04, "존재하지 않는 카테고리입니다.", ERROR),

    // - 장바구니 -
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.C01, "존재하지 않는 상품입니다.", ERROR),
    EXCEEDS_MAX_ORDER_QUANTITY(ExceptionCode.C02, "최대 10개까지 주문 가능합니다."),

    // - 주문 -
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.O01, "존재하지 않는 주문 내역입니다", ERROR),
    ORDER_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.O02, "존재하지 않는 주문 상세 내역입니다.", ERROR),
    SALE_PRODUCT_MISMATCH(HttpStatus.NOT_FOUND, ExceptionCode.O03, "요청한 판매상품 중 일부가 존재하지 않습니다", ERROR),

    // - 재고 -
    OUT_OF_STOCK(ExceptionCode.S01, "상품 재고가 부족합니다."),

    // - FAQ -
    FAQ_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionCode.B03, "존재하지 않는 FAQ입니다.", ERROR),

    // - 챗봇 -
    CHATBOT_CALL_FAIL(HttpStatus.BAD_REQUEST, ExceptionCode.CH04, "챗봇 호출에 실패했습니다.", ERROR);

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
