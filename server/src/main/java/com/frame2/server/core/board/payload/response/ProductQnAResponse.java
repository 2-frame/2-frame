package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.SimpleProductQnA;

public record ProductQnAResponse(SimpleProductQnA simpleProductQnA) {

    public static ProductQnAResponse from(SimpleProductQnA simpleProductQnA) {
        return new ProductQnAResponse(simpleProductQnA);
    }
}
