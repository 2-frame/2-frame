package com.frame2.server.core.board.application;

import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;

public interface ProductQnAdService {

    // 질문 생성
    void questionCreate(ProductQnARegisterRequest request, Long memberId, Long productId);

    // 질문 수정
    void questionModify(ProductQnAModifyRequest request, Long productId, Long productQnAId);

    // 답변
    void answer(ProductQnAAnswerRequest request, Long productId, Long productQnAId);

    // 질문 삭제
    void remove(Long productId, Long productQnAId);

    // 질문 조회
    ProductQnAResponse getProductQnA(Long productId, Long productQnAId);

    // 질문 리스트
    ProductQnAListResponse getProductQnAList(Long productId);

}
