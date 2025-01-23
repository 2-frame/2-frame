package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;

public interface ProductQnAdService {

    // 질문 생성
    ProductQnA questionCreate(ProductQnARegisterRequest request, Long memberId, Long productId);

    // 질문 수정
    ProductQnA questionModify(ProductQnAModifyRequest request, Long productQnAId);

    // 답변
    ProductQnA answer(ProductQnAAnswerRequest request, Long productQnAId);

    // 질문 삭제
    void remove(Long id);

    // 질문 조회
    ProductQnAResponse getProductQnA(Long id);

    // 질문 리스트
    ProductQnAListResponse getProductQnAList();

}
