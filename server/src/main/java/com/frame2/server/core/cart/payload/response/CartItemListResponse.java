package com.frame2.server.core.cart.payload.response;

import com.frame2.server.core.cart.domain.CartItem;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.product.domain.SaleProduct;

public record CartItemListResponse(
        Long id,
        Member member,
        SaleProduct saleProduct,
        int quantity
) {
    public static CartItemListResponse from(CartItem cartItem) {
        return new CartItemListResponse(
                cartItem.getId(),
                cartItem.getMember(),
                cartItem.getSaleProduct(),
                cartItem.getQuantity()
        );
    }
}
