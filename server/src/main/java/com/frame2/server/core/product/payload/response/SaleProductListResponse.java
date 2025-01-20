package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.SaleProduct;
import lombok.Builder;

@Builder
public record SaleProductListResponse(
    Long saleProductId,
    String saleProductName,
    String mainImage,
    int salePrice,
    int saleCount
) {
    public static SaleProductListResponse from(SaleProduct saleProduct) {
        return SaleProductListResponse.builder()
            .saleProductId(saleProduct.getId())
            .saleProductName(saleProduct.getName())
            .salePrice(saleProduct.getSalePrice())
            .mainImage(saleProduct.getMainImage())
            .saleCount(saleProduct.getSaleCount())
            .build();
    }
}