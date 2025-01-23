package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductQnA;

public record ProductQnAModifyRequest(
        String title,
        String question
) {
    public ProductQnA toEntity() {
        return ProductQnA.builder()
                .title(title)
                .question(question)
                .build();
    }
}
