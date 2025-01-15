package com.frame2.server.core.board.payload;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.example.domain.Example;

public record ProductQnARegisterRequest(Long id , String user, String question, String manager, String answer,
                                        boolean answer_YN, boolean isDeleted  ) {
    public ProductQnA toEntity() {
        return ProductQnA.builder()
                .user(user)
                .question(question)
                .answer(answer)
                .manager(manager)
                .build();
    }
}
