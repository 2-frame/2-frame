package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductQnA;

public record ProductQnAAnswerRegisterRequest(
        Long id,
        String answer,
        String manager
) {

    public ProductQnA toEntity() {
        return ProductQnA.builder()
                .answer(answer)
                .manager(manager)
                .answer_YN(true)
                .build();
    }

}
