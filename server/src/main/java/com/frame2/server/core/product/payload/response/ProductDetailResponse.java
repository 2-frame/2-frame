package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Product;
import lombok.Builder;

@Builder
public record ProductDetailResponse(
        Long productId,
        String name,
        int price,
        String brand,
        String manufacturer,
        String origin,
        String description,
        CategoryResponse category
) {
    public static ProductDetailResponse from(Product product) {
        return ProductDetailResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .manufacturer(product.getManufacturer())
                .origin(product.getOrigin())
                .description(product.getDescription())
                .category(CategoryResponse.from(product.getCategory()))
                .build();
    }
}
