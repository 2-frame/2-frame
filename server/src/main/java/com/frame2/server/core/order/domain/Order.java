package com.frame2.server.core.order.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private Long memberId;

    // 대표상품명
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    private int totalPrice;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String recipientContact;

    @Column(nullable = false)
    private String recipientAddress1;

    @Column(nullable = false)
    private String recipientAddress2;

    @Column(nullable = false)
    private String deliveryRequest;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    @Builder
    public Order(Long memberId, String productName, String recipient, String recipientContact,
                 String recipientAddress1, String recipientAddress2, String deliveryRequest) {

        this.memberId = memberId;
        this.productName = productName;
        this.recipient = recipient;
        this.recipientContact = recipientContact;
        this.recipientAddress1 = recipientAddress1;
        this.recipientAddress2 = recipientAddress2;
        this.deliveryRequest = deliveryRequest;
        this.orderStatus = OrderStatus.ORDER_RECIEVED;
    }

    // 주문에 주문 상세 추가
    public void addOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails.addAll(orderDetails);
    }

    // 대표상품명 업데이트
    public void updateProductName(String productName) {
        this.productName = productName;
    }
    
    // 총 주문 금액 업데이트
    public void updateTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
