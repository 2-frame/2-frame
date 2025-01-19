package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.request.*;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;

public interface ProductQnAdService {

    // 질문 생성
    ProductQnA questionCreate(ProductQnARegisterRequest request);

    // 질문 수정
    ProductQnA questionModify(ProductQnAModifyRequest request);

    // 답변
    ProductQnA answer(ProductQnAAnswerRequest request);

    // 답변 수정
    ProductQnA answerModify(ProductQnAAnswerRequest request);

    // 질문 삭제
    void remove(Long id);

    // 질문 조회
    ProductQnAResponse getProductQnA(Long id);

    // 질문 리스트
    ProductQnAListResponse getProductQnAList();

}
