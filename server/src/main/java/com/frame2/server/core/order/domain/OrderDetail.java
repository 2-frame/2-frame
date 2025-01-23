package com.frame2.server.core.order.domain;

import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.product.domain.Stock;
import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Getter
@Table(name = "order_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    // 주문 상세가 취소
        // 주문 상세 필드 변경
        // 재고 복원
        // 주문 상세 상태 변경
    public void cancelOrderDetail() {
        if(!isDeleteStatus()){
            this.delete();
            this.exchangeReturnPossible = false;
            this.exchaneReturnRequested = true;

            Stock stock = this.saleProduct.getStock();
            stock.restoreQuantity(quantity);

            this.order.updateOrderStatus();
        }
    }
}
