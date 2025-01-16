package com.frame2.server.core.order.domain;


import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "orders")
public class Order extends BaseEntity {

    private Long memberId;                    // 회원id
                                                // 판매상품id
    private String prductName;                  // 대표상품명
    private OrderStatus status;                 // 주문 상태
    private int totalPrice;                     // 총 주문 금액
    private String recipientName;               // 받는 사람
    private String recipientContact;            // 받는 사람 연락처
    private String recipientAddress1;           // 배송지 주소1(기본 주소)
    private String recipientAddress2;           // 배송지 주소2(상세 주소)
    private String deliveryRequest;             // 배송 요청 사항

}
