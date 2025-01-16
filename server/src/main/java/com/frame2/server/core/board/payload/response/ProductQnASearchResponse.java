package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.ProductQnA;

public record ProductQnASearchResponse(ProductQnA productQnA) {

    public static ProductQnASearchResponse from(ProductQnA productQnA) {
        return new ProductQnASearchResponse(productQnA);
    }
}
