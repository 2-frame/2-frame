package com.frame2.server.core.example.payload;

import com.frame2.server.core.example.domain.Example;

public record ExampleRegisterRequest(
        String name,
        String description
) {

    public Example toEntity() {
        return Example.builder()
                .name(name)
                .description(description)
                .build();
    }
}
