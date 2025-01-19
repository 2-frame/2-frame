package com.frame2.server.core.product.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String name;
    private int price;
    private String description;
    private String brand;
    private String manufacturer;
    private String origin;
    private String image;
}
