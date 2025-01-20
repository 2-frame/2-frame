package com.frame2.server.core.board.payload.request;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.product.domain.SaleProduct;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public record ProductReviewRequest(
        Long id,
        SaleProduct saleProduct,
        Member member,
        OrderDetail orderDetail,
        int rating,
        String contents,
        String image
) {
    public ProductReview toEntity(){
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
