package com.frame2.server.core.board.payload;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.example.payload.ExampleSearchResponse;
import com.frame2.server.core.example.payload.SimpleExample;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public record ProductQnASearchResponse (List<ProductQnA> productQnAList) {

    public static ProductQnASearchResponse of(List<ProductQnA> productQnALists) {
        return productQnALists.stream()
                .map(productQnALists::from)
                .collect(collectingAndThen(toList(), ExampleSearchResponse::new));
    }

}
