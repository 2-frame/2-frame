package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRegisterRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.response.ProductQnASearchAllResponse;
import com.frame2.server.core.board.payload.response.ProductQnASearchResponse;

public interface ProductQnAdService {

    // 셍성 : 질문
    ProductQnA questionCreate(ProductQnARegisterRequest request);

    // 수정 : 질문 수정
    ProductQnA questionModify(ProductQnAModifyRequest request, Long id);

    // 수정 : 답변 수정
    ProductQnA answer(ProductQnAAnswerRegisterRequest request, Long id);

    // 질문 삭제
    void remove(Long id);

    // 질문 조회
    ProductQnASearchResponse searchOne(Long id);

    // 질문 리스트
    ProductQnASearchAllResponse searchAll();

}
