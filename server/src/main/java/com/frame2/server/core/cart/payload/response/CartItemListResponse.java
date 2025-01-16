package com.frame2.server.core.cart.payload.response;

import com.frame2.server.core.cart.domain.CartItem;

public record CartItemListResponse(
        Long id,
        Long memberId,
        Long saleProductId,
        String saleProductName,
        String saleProductMainImage,
        int quantity
) {
    public static CartItemListResponse from(CartItem cartItem) {
        return new CartItemListResponse(
                cartItem.getId(),
                cartItem.getMember().getId(),
                cartItem.getSaleProduct().getId(),
                cartItem.getSaleProduct().getName(),
                cartItem.getSaleProduct().getMainImage(),
                cartItem.getQuantity()
        );
    }
}
