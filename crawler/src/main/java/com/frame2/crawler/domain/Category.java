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
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_category_id")
    private Category rootCategory;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    private String categoryName;

    @Builder
    public Category(Category rootCategory, Category parentCategory, String categoryName) {
        this.rootCategory = rootCategory;
        this.parentCategory = parentCategory;
        this.categoryName = categoryName;
    }
}
