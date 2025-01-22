package com.frame2.crawler.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_product_id")
    private SaleProduct saleProduct;

    private int quantity;

    @Builder
    public Stock(SaleProduct saleProduct, int quantity) {
        this.saleProduct = saleProduct;
        this.quantity = quantity;
    }

    // 재고 검증 및 차감 메서드
    public void reduceQuantity(int requestQuantity) {
        // 재고 검증
        if (requestQuantity > this.quantity) {
            throw new IllegalArgumentException(
                    String.format("상품ID: %d, 요청 수량: %d, 주문 가능 수량: %d",
                            saleProduct.getId(), requestQuantity, this.quantity));
        }
        // 재고 차감
        this.quantity -= requestQuantity;
    }
}
