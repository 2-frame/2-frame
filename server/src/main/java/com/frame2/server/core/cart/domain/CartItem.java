package com.frame2.server.core.cart.domain;

import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.support.entity.BaseEntity;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "cart_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseEntity {

    private static final int MAX_QUANTITY = 10;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_product_id", nullable = false)
    private SaleProduct saleProduct;

    @Column(nullable = false)
    private int quantity;

    @Builder
    public CartItem(Member member, SaleProduct saleProduct, int quantity) {
        this.member = member;
        this.saleProduct = saleProduct;
        this.quantity = quantity;
    }

    // 수량 검증 로직
    public void tryUpdateQuantity(int quantity, boolean isDirectChange) {
        // 장바구니에서 수량을 직접 변경하는 경우
        if (isDirectChange) {
            updateQuantity(quantity);
        }
        // 판매상품 페이지에서 장바구니로 상품을 추가하는 경우
        else {
            int newQuantity = this.quantity + quantity;

            // 현재 수량에 새로운 수량을 더한 값이 최대 수량을 초과하면 예외를 던짐
            if (newQuantity > MAX_QUANTITY) {
                throw new DomainException(ExceptionType.EXCEEDS_MAX_ORDER_QUANTITY);
            }
            updateQuantity(newQuantity);
        }
    }

    // 수량 변경 로직
    private void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}
