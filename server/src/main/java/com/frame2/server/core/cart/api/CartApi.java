package com.frame2.server.core.cart.api;

import com.frame2.server.core.cart.application.CartService;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartApi implements CartApiSpec {

    private final CartService cartService;

    @Override
    @GetMapping
    public ResponseEntity<List<CartItemListResponse>> getAllCartItems() {
        List<CartItemListResponse> response = cartService.getAllCartItems();
        return ResponseEntity.ok().body(response);
    }
}
