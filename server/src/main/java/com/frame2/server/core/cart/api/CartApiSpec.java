package com.frame2.server.core.cart.api;

import com.frame2.server.core.cart.payload.request.CartItemRequest;
import com.frame2.server.core.cart.payload.request.QuantityRequest;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import com.frame2.server.core.support.request.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "장바구니 API")
public interface CartApiSpec {

    @Operation(summary = "장바구니 상품 조회")
    List<CartItemListResponse> getCartItems(User user);

    @Operation(summary = "장바구니 상품 담기")
    List<CartItemListResponse> addCartItem(User user, CartItemRequest request);

    @Operation(summary = "장바구니 상품 수량 변경")
    List<CartItemListResponse> changeCartItemQuantity(User user, QuantityRequest request);

    @Operation(summary = "장바구니 상품 삭제")
    void removeCartItem(User user, Long cartItemId);
}
