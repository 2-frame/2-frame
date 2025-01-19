package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.product.domain.SaleProduct;

public record ProductReviewResponse(
        Long id,
        SaleProduct saleProduct,
        Member member,
        int rating,
        String contents,
        String image
) {
    public static ProductReviewResponse from(ProductReview productReview) {
        return new ProductReviewResponse(
                productReview.getId(),
                productReview.getSaleProduct(),
                productReview.getMember(),
                productReview.getRating(),
                productReview.getContents(),
                productReview.getImage()
        );

    }

}
