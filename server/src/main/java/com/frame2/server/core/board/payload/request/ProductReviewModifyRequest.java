package com.frame2.server.core.board.payload.request;


import com.frame2.server.core.board.domain.ProductReview;

public record ProductReviewModifyRequest(
        Long id,
        int rating,
        String contents,
        String image
) {
    public ProductReview toEntity(){
        return ProductReview.builder()
                .rating(rating)
                .contents((contents))
                .image(image)
                .build();
    }
}
