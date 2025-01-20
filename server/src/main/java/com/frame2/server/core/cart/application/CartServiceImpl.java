package com.frame2.server.core.cart.application;

import com.frame2.server.core.cart.infrastructure.CartItemRepository;
import com.frame2.server.core.cart.payload.request.CartItemRequest;
import com.frame2.server.core.cart.payload.request.QuantityRequest;
import com.frame2.server.core.cart.payload.response.CartItemListResponse;
import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.product.infrastructure.SaleProductRepository;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import java.util.List;
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
        cartItemRepository.findByMemberIdAndSaleProductId(memberId, cartItemRequest.saleProductId())
                // 이미 장바구니에 해당 상품이 있다면 수량만 업데이트
                .ifPresentOrElse(item -> item.addSameItemToCart(cartItemRequest.quantity()),
                        // 장바구니에 상품이 없으면 새로 생성하여 저장
                        () -> createCartItem(memberId, cartItemRequest));
    }

    @Override
    @Transactional
    public void changeCartItemQuantity(Long memberId, QuantityRequest quantityRequest) {
        cartItemRepository.findByMemberIdAndSaleProductId(memberId, quantityRequest.saleProductId())
                .orElseThrow(() -> new DomainException(ExceptionType.CART_ITEM_NOT_FOUND))
                .changeQuantity(quantityRequest.quantity());
    }

    @Override
    @Transactional
    public void createCartItem(Long memberId, CartItemRequest cartItemRequest) {
        var member = memberRepository.findOne(memberId);
        var saleProduct = saleProductRepository.findOne(cartItemRequest.saleProductId());

        // quantity 값은 cartItemRequest 안에 담겨 있다.(== this.quantity)
        cartItemRepository.save(cartItemRequest.toEntity(member, saleProduct));
    }

    @Override
    @Transactional
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.findOne(cartItemId).delete();
    }
}
