package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.AnswerStatus;
import com.frame2.server.core.board.domain.ProductQnA;

public record ProductQnAAnswerRequest(
        Long id,
        String answer,
        String manager
) {
    public ProductQnA toEntity() {
        return ProductQnA.builder()
                .answer(answer)
                .manager(manager)
                .answer_YN(AnswerStatus.Y)
                .build();
    }
}
