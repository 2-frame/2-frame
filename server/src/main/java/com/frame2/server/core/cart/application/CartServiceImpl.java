package com.frame2.server.core.cart.application;

import com.frame2.server.core.cart.infrastructure.CartItemRepository;
import com.frame2.server.core.cart.payload.request.CartItemRequest;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import java.util.List;

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
        var cartItem = cartItemRepository.findByMemberIdAndSaleProductId(memberId, cartItemRequest.saleProductId());

        // 이미 있는 상품이라면, 수량만 업데이트 한다.
        cartItem.ifPresent(item -> item.tryUpdateQuantity(cartItemRequest.quantity()));

        // empty 라면, 새로 저장한다
        if (cartItem.isEmpty()) {
            var member = memberRepository.findOne(memberId);
            var saleProduct = saleProductRepository.findOne(cartItemRequest.saleProductId());

            // quantity 값은 cartItemRequest 안에 담겨 있다.(== this.quantity)
            cartItemRepository.save(cartItemRequest.toEntity(member, saleProduct));
        }
    }
}
