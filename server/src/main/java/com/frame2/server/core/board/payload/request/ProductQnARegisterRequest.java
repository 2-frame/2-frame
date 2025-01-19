package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.product.domain.Product;

public record ProductQnARegisterRequest(
        Member member,
        Product product,
        String title,
        String question
) {
    public ProductQnA toEntity() {
        return ProductQnA.builder()
                .member(member)
                .product(product)
                .title(title)
                .question(question)
                .build();
    }
}
