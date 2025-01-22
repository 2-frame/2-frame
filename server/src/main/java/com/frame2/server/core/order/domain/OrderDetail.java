package com.frame2.server.core.order.domain;

import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail extends BaseEntity{
    
    // 주문 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    // 판매상품 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_prodcut_id", nullable = false)
    private SaleProduct saleProduct;

    // 판매상품 수량
    @Column(nullable = false)
    private int quantity;

    // 판매상품 가격
    @Column(nullable = false)
    private int price;
    
    // 배송상태
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    // 배송 시작일
    private LocalDate deliveryStartDate;

    // 배송 종료일
    private LocalDate deliveryEndDate;
    
    // 교환, 반품 신청 가능 여부
    private boolean exchangeReturnPossible;

    // 교환, 반품 신청 여부
    private boolean exchaneReturnRequested;

    @Builder
    public OrderDetail(Order order, SaleProduct saleProduct, int quantity, int price) {
        this.order = order;
        this.saleProduct = saleProduct;
        this.quantity = quantity;
        this.price = price;
        this.deliveryStatus = DeliveryStatus.PACKAGING;
        this.exchangeReturnPossible = true;
        this.exchaneReturnRequested = false;
    }

    // 주문 상세가 취소되면
        // 교환, 반품 신청 가능여부는 false로 변경
        // 교환, 반품 신청 여부는 true로 변경
        // 주문(Order) 상태 변경 - 주문 부분 취소
    public void cancelOrderDetail() {
        this.delete();
        this.exchangeReturnPossible = false;
        this.exchaneReturnRequested = true;
        this.order.updateOrderStatus(OrderStatus.ORDER_PARTICAL_CANCELED);
    }
}
