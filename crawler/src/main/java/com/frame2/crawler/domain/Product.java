package com.frame2.crawler.domain;

import jakarta.persistence.Entity;
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

    @Builder
    public Product(Category category, String name, int price, String description, String brand, String manufacturer,
                   String origin) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.origin = origin;
    }
}
