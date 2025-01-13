package com.frame2.server.core.example.payload;

import com.frame2.server.core.example.domain.Example;

public record SimpleExample(
        Long id,
        String title,
        String description
) {

    public static SimpleExample from(Example example) {
        return new SimpleExample(
                example.getId(),
                example.getName(),
                example.getDescription()
        );
    }
}
