package com.frame2.server.core.board.payload.request;

import brave.internal.Nullable;
import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.product.domain.SaleProduct;
import jakarta.validation.constraints.*;

public record ProductReviewRequest(

        @NotNull
        Long saleProductId,

        @NotNull
        Long orderDetailId,

        @Min(1)
        @Max(5)
        @NotNull
        int rating,

        @NotBlank
        @Size(min = 10, max = 1000, message = "리뷰는 최소 10자에서 1000자까지 가능합니다.")
        String contents,

        @Nullable
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
