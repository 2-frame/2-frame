package com.frame2.server.core.board.payload;

import com.frame2.server.core.board.domain.AnswerStatus;
import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.member.domain.Member;

import java.time.LocalDateTime;
import java.util.Date;

public record SimpleProductQnA(Long id,
                               Member member,
                               String title,
                               String question,
                               String answer,
                               AnswerStatus answer_YN,
                               LocalDateTime answer_date,
                               LocalDateTime createdAt
) {

    public static SimpleProductQnA from(ProductQnA productQnA) {
        return new SimpleProductQnA(
                productQnA.getId(),
                productQnA.getMember(),
                productQnA.getTitle(),
                productQnA.getQuestion(),
                productQnA.getAnswer(),
                productQnA.getAnswer_YN(),
                productQnA.getAnswer_date(),
                productQnA.getCreatedAt()
        );
    }
}
