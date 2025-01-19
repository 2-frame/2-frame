package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.product.domain.Stock;
import lombok.Builder;

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
    @Builder
    public SaleProductDetailResponse {}

    public static SaleProductDetailResponse of(SaleProduct sp, Stock stock) {
        return SaleProductDetailResponse.builder()
                .saleProductId(sp.getId())
                .saleProductName(sp.getName())
                .salePrice(sp.getSalePrice())
                .saleCount(sp.getSaleCount())
                .mainImage(sp.getMainImage())
                .subImage(sp.getSubImage())
                .descriptionImage(sp.getDescriptionImage())
                .product(ProductDetailResponse.from(sp.getProduct()))
                .option(sp.getOption() != null ? OptionResponse.from(sp.getOption()) : null)
                .stockQuantity(stock != null ? stock.getQuantity() : 0)
                .build();
    }
}