package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.SaleProduct;
import lombok.Builder;

public record SaleProductListResponse(
    Long saleProductId,
    String saleProductName,
    String mainImage,
    int salePrice,
    int saleCount
) {
    @Builder
    public SaleProductListResponse {}

    public static SaleProductListResponse from(SaleProduct sp) {
        return SaleProductListResponse.builder()
            .saleProductId(sp.getId())
            .saleProductName(sp.getName())
            .salePrice(sp.getSalePrice())
            .mainImage(sp.getMainImage())
            .saleCount(sp.getSaleCount())
            .build();
    }
}