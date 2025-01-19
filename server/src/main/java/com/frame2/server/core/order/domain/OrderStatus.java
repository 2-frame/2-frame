package com.frame2.server.core.order.domain;

public enum OrderStatus {

    ORDER_RECIEVED,         // 주문접수
    ORDER_COMPLETED,        // 주문완료
    ORDER_CANCELED,         // 주문취소
    PURCHASED_CONFIRMED;    // 구매확정

}