package com.frame2.server.core.cart.api;

import com.frame2.server.core.cart.payload.request.CartItemRequest;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.request.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Tag(name = "장바구니 API")
public interface CartApiSpec {

    @Operation(summary = "장바구니 상품 조회")
    List<CartItemListResponse> getCartItems(User user);

    @Operation(summary = "장바구니 상품 추가")
    void addCartItem(User user, CartItemRequest cartItemRequest);
}
