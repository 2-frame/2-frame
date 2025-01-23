package com.frame2.crawler.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product_options")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Option extends BaseEntity {
    private String name;

    @Column(name = "option_value")
    private String value;
    private int additionalPrice;

    @Builder
    public Option(String name, String value, int additionalPrice) {
        this.name = name;
        this.value = value;
        this.additionalPrice = additionalPrice;
    }
}
