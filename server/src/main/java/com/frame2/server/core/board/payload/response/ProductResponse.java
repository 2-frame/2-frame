package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.product.domain.Product;

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