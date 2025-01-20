package com.frame2.server.core.cart.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record IdRequest(
        @NotNull(message = "선택된 상품이 없습니다.")
        Long cartItemtId
) {
}
