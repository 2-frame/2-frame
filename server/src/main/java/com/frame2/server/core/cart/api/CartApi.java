package com.frame2.server.core.cart.api;

import com.frame2.server.core.cart.application.CartService;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import com.frame2.server.core.member.payload.SignInInfo;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartApi implements CartApiSpec {

    private final CartService cartService;

    @Override
    @GetMapping
    public List<CartItemListResponse> getCartItems(HttpSession session) {
        SignInInfo signInInfo = (SignInInfo) session.getAttribute("signInInfo");

        if (signInInfo == null) {
            throw new DomainException(ExceptionType.UNAUTHORIZED_ERROR);
        }

        return cartService.getCartItemsByMemberId(signInInfo.memberId());
    }
}
