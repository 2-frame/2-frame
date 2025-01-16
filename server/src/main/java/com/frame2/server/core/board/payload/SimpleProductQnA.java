package com.frame2.server.core.board.payload;

import com.frame2.server.core.board.domain.ProductQnA;

import java.time.LocalDateTime;
import java.util.Date;

public record SimpleProductQnA(Long id,
                               String user_id,
                               String question,
                               String answer,
                               boolean answer_YN,
                               Date answer_date,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {

    public static SimpleProductQnA from(ProductQnA productQnA) {
        return new SimpleProductQnA(
                productQnA.getId(),
                productQnA.getUser_id(),
                productQnA.getQuestion(),
                productQnA.getAnswer(),
                productQnA.isAnswer_YN(),
                productQnA.getAnswer_date(),
                productQnA.getCreatedAt(),
                productQnA.getUpdatedAt()
        );
    }
}
