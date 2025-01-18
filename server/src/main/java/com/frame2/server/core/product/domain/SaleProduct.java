package com.frame2.server.core.product.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    private String name;
    private String mainImage;
    private String subImage;
    private String descriptionImage;
    private String unit;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    private ProductDisclosure productDisclosure;

    @Builder
    public SaleProduct(String name, String mainImg) {
        this.name = name;
        this.mainImage = mainImg;
    }
}
