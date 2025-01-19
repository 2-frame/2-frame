package com.frame2.server.core.cart.application;

import com.frame2.server.core.cart.domain.CartItem;
import com.frame2.server.core.cart.infrastructure.CartItemRepository;
import com.frame2.server.core.cart.payload.request.CartItemRequest;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import java.util.List;
import java.util.stream.Collectors;

import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.product.infrastructure.SaleProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final SaleProductRepository saleProductRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CartItemListResponse> getCartItems(Long memberId) {
        return cartItemRepository.findAllByMemberId(memberId)
                .stream()
                .map(CartItemListResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public void addCartItem(Long memberId, CartItemRequest cartItemRequest) {
        var member = memberRepository.findOne(memberId);
        var saleProduct = saleProductRepository.findOne(cartItemRequest.saleProductId());

        cartItemRepository.save(cartItemRequest.toEntity(member, saleProduct));
    }
}
