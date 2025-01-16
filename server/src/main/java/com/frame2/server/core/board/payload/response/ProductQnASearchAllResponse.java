package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.ProductQnA;

import java.util.List;

public record ProductQnASearchAllResponse(List<ProductQnA> list) {

    // static 메소드로 객체 생성
    public static ProductQnASearchAllResponse from(List<ProductQnA> list) {
        return new ProductQnASearchAllResponse(list);
    }
}
