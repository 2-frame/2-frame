package com.frame2.server.core.product.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    // 재고 검증 및 차감 메서드
    public void reduceQuantity(int requestQuantity) {
        // 재고 검증
        if (requestQuantity > this.quantity) {
            throw new DomainException(ExceptionType.OUT_OF_STOCK,
                    String.format("상품ID: %d, 요청 수량: %d, 주문 가능 수량: %d",
                            saleProduct.getId(), requestQuantity, this.quantity));
        }
        // 재고 차감
        this.quantity -= requestQuantity;
    }

    // 재고 가산 메서드
    public void restoreQuantity(int requestQuantity) {
        this.quantity += requestQuantity;
    }
}
