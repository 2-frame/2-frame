package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;

import java.util.List;

public interface ProductReviewService {
    // 생성
    ProductReview productReviewCreate(ProductReviewRequest request);

    // 수정
    ProductReview productReviewModify(ProductReviewModifyRequest request);

    // 삭제
    void remove(Long id);

    // 단건 조회
    ProductReviewResponse getProductReview(Long id);

    // 전체 조회
    List<ProductReviewResponse> getProductReviewList();
}
