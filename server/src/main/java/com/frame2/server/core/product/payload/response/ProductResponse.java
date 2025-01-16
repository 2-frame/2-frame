package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Product;
import lombok.Builder;

public record ProductResponse(
        Long id,
        String name,
        int price,
        String description
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription());
    }
}
