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
    
    //재고 차감 메서드
    public void reduceQuantity(int requestQuantity) {
        this.quantity -= requestQuantity;
    }

    // 재고 검증 메서드
    public void validateQuantity(int requestQuantity) {
        if (requestQuantity > this.quantity) {
            throw new DomainException(ExceptionType.OUT_OF_STOCK,
                    "상품ID: "+this.saleProduct.getId() +", "+
                    "요청 수량: " + requestQuantity +", "+
                    "주문 가능 수량: " + this.quantity);
        }
    }
}
