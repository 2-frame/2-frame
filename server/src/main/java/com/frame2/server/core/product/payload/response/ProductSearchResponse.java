package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Product;
import lombok.Builder;

public record ProductSearchResponse(
        Long id,
        String name,
        int price
) {
    public static ProductSearchResponse from(Product product) {
        return new ProductSearchResponse(product.getId(), product.getName(), product.getPrice());
    }
}
