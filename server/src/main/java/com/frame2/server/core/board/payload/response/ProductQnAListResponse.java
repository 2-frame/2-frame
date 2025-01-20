package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.SimpleProductQnA;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public record ProductQnAListResponse(List<SimpleProductQnA> simpleProductQnAList

) {
    public static ProductQnAListResponse of(List<ProductQnA> list) {
        return list.stream()
                .map(SimpleProductQnA::from)
                .collect(collectingAndThen(toList(), ProductQnAListResponse::new));
    }
}
