package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Product;
import lombok.Builder;

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
    @Builder
    public ProductDetailResponse {}

    public static ProductDetailResponse from(Product p) {
        return ProductDetailResponse.builder()
                .productId(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .brand(p.getBrand())
                .manufacturer(p.getManufacturer())
                .origin(p.getOrigin())
                .description(p.getDescription())
                .category(CategoryResponse.from(p.getCategory()))
                .build();
    }
}
