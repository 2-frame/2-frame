package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.ProductQnASearchResponse;
import com.frame2.server.core.example.payload.ExampleRegisterRequest;

import java.util.List;

public interface ProductQnAdService {

    // Question
    ProductQnA questionCreate(ProductQnARegisterRequest request);

    // 수정 : 질문 수정
    ProductQnA questionModify(ProductQnARegisterRequest request);

    // 수정 : Answer
    ProductQnA answer();

    // Question 삭제
    void remove();

    // 조회가 꼭 필요한지 모르겠으나 일단 만듦
    ProductQnASearchResponse searchOne();

//    ProductQnA searchOne();

    List<ProductQnA> searchAll();

}
