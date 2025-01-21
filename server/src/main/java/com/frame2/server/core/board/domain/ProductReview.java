package com.frame2.server.core.board.domain;

import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "product_reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductReview extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_product_id", nullable = false)
    private SaleProduct saleProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetail orderDetail;

    // 평점
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int rating;

    //리뷰 내용
    @Column(nullable = false)
    private String contents;

    private String image;

    public ProductReview updateProductReview(ProductReview newProductReview) {
        this.rating = newProductReview.getRating();
        this.contents = newProductReview.getContents();
        this.image = newProductReview.getImage();
        return this;
    }
}
