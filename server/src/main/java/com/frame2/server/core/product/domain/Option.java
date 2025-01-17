package com.frame2.server.core.product.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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
}
