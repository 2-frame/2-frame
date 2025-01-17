package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductQnA;

public record ProductQnAModifyRequest(

        String user_id,
        String question) {

    public ProductQnA toEntity() {
        return ProductQnA.builder()
                .user_id(user_id)
                .question(question)
                .build();
    }

}
