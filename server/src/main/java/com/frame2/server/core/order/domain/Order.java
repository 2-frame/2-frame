package com.frame2.server.core.order.domain;

import com.frame2.server.core.product.domain.Stock;
import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Column(nullable = false)
    private Long memberId;

    // 대표상품명
    @Column(name = "product_name")
    private String mainProductName;

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
    public Order(Long memberId, String mainProductName, String recipient, String recipientContact,
                 String recipientAddress1, String recipientAddress2, String deliveryRequest) {

        this.memberId = memberId;
        this.mainProductName = mainProductName;
        this.recipient = recipient;
        this.recipientContact = recipientContact;
        this.recipientAddress1 = recipientAddress1;
        this.recipientAddress2 = recipientAddress2;
        this.deliveryRequest = deliveryRequest;
        this.orderStatus = OrderStatus.ORDER_RECEIVED;
    }

    // 주문에 주문 상세 추가
    public void addOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails.addAll(orderDetails);
    }

    // 대표상품명 업데이트
    public void updateProductName() {
        String mainProductName = orderDetails.getFirst().getSaleProduct().getName();
        if(orderDetails.size() == 1){
            this.mainProductName = mainProductName;
        } else {
            this.mainProductName = mainProductName + " 외 " + (orderDetails.size() - 1)+"건";
        }
    }
    
    // 총 주문 금액 업데이트
    public void updateTotalPrice() {
        int totalPrice = orderDetails.stream()
                .mapToInt(orderDetail -> {
                    return orderDetail.getPrice() * orderDetail.getQuantity();
                })
                .sum();
        this.totalPrice = totalPrice;
    }
    
    // 주문 취소 -> 모든 주문 상세가 순차적으로 취소
        // 주문은 주문 상세에게 삭제 명령만 내리도록 수정
        // 재고 복원, 주문 상태 변경
    public void cancelOrder() {
        orderDetails.forEach(OrderDetail::cancelOrderDetail);
        this.updateOrderStatus();
    }

    // 주문 상태 변경 메서드
        // 주문 상세가 모두 삭제되면 ORDER_CANCELED
        // 주문 상세가 일부 삭제되면 ORDER_PARTICAL_CANCELED
    public void updateOrderStatus() {
        boolean allCanceled = orderDetails.stream().allMatch(OrderDetail::isDeleteStatus);
        orderStatus = allCanceled ? OrderStatus.ORDER_CANCELED : OrderStatus.ORDER_PARTICAL_CANCELED;
        if(allCanceled){
            this.delete();
        }
    }
}
