package com.frame2.server.core.cart.application;

import com.frame2.server.core.cart.payload.request.CartItemRequest;
import com.frame2.server.core.cart.payload.request.QuantityRequest;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import java.util.List;

public interface CartService {

    List<CartItemListResponse> getCartItems(Long memberId);

    void addCartItem(Long memberId, CartItemRequest cartItemRequest);

    void changeCartItemQuantity(Long memberId, QuantityRequest quantityRequest);

    void createCartItem(Long memberId, CartItemRequest cartItemRequest);

    void removeCartItem(Long cartItemId);
}
