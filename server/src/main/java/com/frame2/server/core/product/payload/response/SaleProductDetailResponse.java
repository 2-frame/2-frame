package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.SaleProduct;
import lombok.Builder;

@Builder
public record SaleProductDetailResponse(
    Long saleProductId,
    String saleProductName,
    int salePrice,
    int saleCount,
    String mainImage,
    String subImage,
    String descriptionImage,
    ProductDetailResponse product,
    OptionResponse option,
    int stockQuantity
) {
    public static SaleProductDetailResponse from(SaleProduct saleProduct) {
        return SaleProductDetailResponse.builder()
                .saleProductId(saleProduct.getId())
                .saleProductName(saleProduct.getName())
                .salePrice(saleProduct.getSalePrice())
                .saleCount(saleProduct.getSaleCount())
                .mainImage(saleProduct.getMainImage())
                .subImage(saleProduct.getSubImage())
                .descriptionImage(saleProduct.getDescriptionImage())
                .product(ProductDetailResponse.from(saleProduct.getProduct()))
                .option(OptionResponse.from(saleProduct.getOption()))
                .stockQuantity(saleProduct.getStock().getQuantity())
                .build();
    }
}