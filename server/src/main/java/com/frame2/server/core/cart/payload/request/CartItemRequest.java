package com.frame2.server.core.cart.payload.request;

import com.frame2.server.core.cart.domain.CartItem;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.product.domain.SaleProduct;

public record CartItemRequest (
    Long saleProductId,
    int quantity
) {
    public CartItem toEntity(Member member, SaleProduct saleProduct) {
        return CartItem.builder()
                .member(member)
                .saleProduct(saleProduct)
                .quantity(quantity)
                .build();
    }
}
