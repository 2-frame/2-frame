package com.frame2.server.core.cart.api;

import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "장바구니 API")
public interface CartApiSpec {

    ResponseEntity<List<CartItemListResponse>> getAllCartItems();
}
