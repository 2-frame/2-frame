package com.frame2.server.core.cart.payload.request;

import com.frame2.server.core.cart.domain.CartItem;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.product.domain.SaleProduct;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record QuantityRequest(
        @NotNull(message = "필수 옵션을 선택해주세요.")
        Long saleProductId,

        @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
        @Max(value = 10, message = "최대 10개까지 주문 가능합니다.")
        int quantity
) {
    public CartItem toEntity(Member member, SaleProduct saleProduct) {
        return CartItem.builder()
                .member(member)
                .saleProduct(saleProduct)
                .quantity(quantity)
                .build();
    }
}
