package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductQnA;

import java.time.LocalDateTime;

public record ProductQnAAnswerRequest(
        String answer,
        String manager
) {
    public ProductQnA toEntity() {
        return ProductQnA.builder()
                .answer(answer)
                .manager(manager)
                .answerYN(true)
                .answerDate(LocalDateTime.now())
                .build();
    }

}
