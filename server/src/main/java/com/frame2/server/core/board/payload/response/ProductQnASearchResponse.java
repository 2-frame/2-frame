package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.SimpleProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRegisterRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.example.payload.ExampleSearchResponse;
import com.frame2.server.core.example.payload.SimpleExample;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public record ProductQnASearchResponse(ProductQnA productQnA) {

    public static ProductQnASearchResponse from(ProductQnA productQnA) {
        return new ProductQnASearchResponse(productQnA) ;
    }
}
