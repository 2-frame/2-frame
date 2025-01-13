package com.frame2.server.core.example.domain;

import com.frame2.server.core.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "examples")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Example extends BaseEntity {

    private String name;
    private String description;

    @Builder
    public Example(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
