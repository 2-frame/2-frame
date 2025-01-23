package com.frame2.server.core.board.application;

import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;

import java.util.List;

public interface ProductReviewService {
    // 생성
    void productReviewCreate(ProductReviewRequest request, Long memberId, Long saleProductId);

    // 수정
    void productReviewModify(ProductReviewModifyRequest request, Long productReviewId);

    // 삭제
    void remove(Long productReviewId);

    // 단건 조회
    ProductReviewResponse getProductReview(Long productReviewId);

    // 한 판매상품에 대한 전체 맂뷰 조회
    List<ProductReviewResponse> getProductReviewList(Long saleProductId);
}
