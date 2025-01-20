package com.frame2.server.core.product.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "sale_products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    @OneToOne(mappedBy = "saleProduct")
    private Stock stock;

    private String name;
    private String mainImage;
    private String subImage;
    private String descriptionImage;
    private String unit;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    private ProductDisclosure productDisclosure;

    // 필터링을 위한 컬럼들
    private int salePrice;
    private int saleCount;

    @Builder
    public SaleProduct(Stock stock, String name, String mainImg, String subImage, String descriptionImage,
                       String unit, int salePrice, int saleCount,
                       ProductStatus productStatus, ProductDisclosure productDisclosure) {
        this.stock = stock;
        this.name = name;
        this.mainImage = mainImg;
        this.subImage = subImage;
        this.descriptionImage = descriptionImage;
        this.unit = unit;
        this.productStatus = productStatus;
        this.productDisclosure = productDisclosure;
        this.salePrice = salePrice;
        this.saleCount = saleCount;
    }
}
