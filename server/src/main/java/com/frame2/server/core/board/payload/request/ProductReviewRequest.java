package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.product.domain.SaleProduct;

public record ProductReviewRequest(
        Long saleProductId,
        Long orderDetailId,
        int rating,
        String contents,
        String image
) {
    public ProductReview toEntity(Member member, SaleProduct saleProduct, OrderDetail orderDetail) {
        return ProductReview.builder()
                .saleProduct(saleProduct)
                .member(member)
                .orderDetail(orderDetail)
                .rating(rating)
                .contents(contents)
                .image(image)
                .build();
    }
}
