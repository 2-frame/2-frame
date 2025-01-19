package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.member.domain.Member;

public record ProductQnAModifyRequest(
        Long id,
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
