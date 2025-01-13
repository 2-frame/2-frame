package com.frame2.server.core.example.payload;

import static java.util.stream.Collectors.toList;

import com.frame2.server.core.example.domain.Example;
import java.util.List;
import java.util.stream.Collectors;

public record ExampleSearchResponse(
        List<SimpleExample> examples
) {
    public static ExampleSearchResponse of(List<Example> examples) {
        return examples.stream()
                .map(SimpleExample::from)
                .collect(Collectors.collectingAndThen(toList(), ExampleSearchResponse::new));
    }
}
