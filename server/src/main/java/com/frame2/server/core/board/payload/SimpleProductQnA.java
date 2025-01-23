package com.frame2.server.core.board.payload;

import com.frame2.server.core.board.domain.AnswerStatus;
import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.response.ProductResponse;
import com.frame2.server.core.member.payload.response.MyInformationResponse;

import java.time.LocalDateTime;

public record SimpleProductQnA(
        Long id,
        MyInformationResponse member,
        ProductResponse product,
        String title,
        String question,
        String answer,
        LocalDateTime createdAt, // 질문 날짜
        AnswerStatus answerYN, //답변 여부
        LocalDateTime answerDate // 답변 날짜
) {
    public static SimpleProductQnA from(ProductQnA productQnA) {
        return new SimpleProductQnA(
                productQnA.getId(),
                MyInformationResponse.from(productQnA.getMember()),
                ProductResponse.from(productQnA.getProduct()),
                productQnA.getTitle(),
                productQnA.getQuestion(),
                productQnA.getAnswer(),
                productQnA.getCreatedAt(),
                productQnA.getAnswerYN(),
                productQnA.getAnswerDate()
        );
    }
}
