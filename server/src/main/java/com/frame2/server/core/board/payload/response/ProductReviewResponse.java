package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.member.payload.response.MyInformationResponse;
import com.frame2.server.core.product.payload.response.SaleProductDetailResponse;

public record ProductReviewResponse(
        Long id,
        SaleProductDetailResponse saleProduct,
        MyInformationResponse member,
        int rating,
        String contents,
        String image
) {
    public static ProductReviewResponse from(ProductReview productReview) {
        return new ProductReviewResponse(
                productReview.getId(),
                SaleProductDetailResponse.from(productReview.getSaleProduct()),
                MyInformationResponse.from(productReview.getMember()),
                productReview.getRating(),
                productReview.getContents(),
                productReview.getImage()
        );
    }
}
