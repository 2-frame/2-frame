package com.frame2.server.core.cart.application;

import com.frame2.server.core.cart.infrastructure.CartItemRepository;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CartItemListResponse> getCartItems(Long memberId) {
        return cartItemRepository.findAllByMemberId(memberId)
                .stream()
                .map(CartItemListResponse::from)
                .toList();
    }
}
