package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Product;
import lombok.Builder;

public record ProductListResponse(
        Long id,
        String name,
        int price
) {
    public static ProductListResponse from(Product product) {
        return new ProductListResponse(product.getId(), product.getName(), product.getPrice());
    }
}
