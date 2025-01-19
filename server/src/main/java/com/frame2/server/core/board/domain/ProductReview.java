package com.frame2.server.core.board.domain;

import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product_reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductReview extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sale_product_id", nullable = false)
    private SaleProduct saleProduct;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetail orderDetail;

    // 평점
    @Column(nullable = false)
    private int rating;

    //리뷰 내용
    @Column(nullable = false)
    private String contents;

    private String image;
}
