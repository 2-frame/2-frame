package com.frame2.server.core.cart.api;

import com.frame2.server.core.cart.application.CartService;
import com.frame2.server.core.cart.payload.request.CartItemRequest;
import com.frame2.server.core.cart.payload.request.QuantityRequest;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.annotations.MemberOnly;
import com.frame2.server.core.support.request.User;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartApi implements CartApiSpec {

    private final CartService cartService;

    @Override
    @MemberOnly
    @GetMapping
    public List<CartItemListResponse> getCartItems(@Auth User user) {
        return cartService.getCartItems(user.id());
    }

    @Override
    @MemberOnly
    @PostMapping
    public List<CartItemListResponse> addCartItem(@Auth User user,
                                                  @RequestBody @Valid CartItemRequest request) {
        cartService.addCartItem(user.id(), request);

        return cartService.getCartItems(user.id());
    }

    @Override
    @MemberOnly
    @PatchMapping
    public List<CartItemListResponse> changeCartItemQuantity(@Auth User user,
                                                             @RequestBody @Valid QuantityRequest request) {
        cartService.changeCartItemQuantity(user.id(), request);

        return cartService.getCartItems(user.id());
    }

    @Override
    @MemberOnly
    @PatchMapping("/{cartItemId}")
    public void removeCartItem(@Auth User user, @PathVariable("cartItemId") Long cartItemId) {
        cartService.removeCartItem(cartItemId);
    }
}
