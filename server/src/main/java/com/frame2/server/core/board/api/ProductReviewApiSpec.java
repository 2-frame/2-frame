package com.frame2.server.core.board.api;

import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;
import com.frame2.server.core.support.request.User;
import jdk.jfr.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Category("상품 리뷰 api")
public interface ProductReviewApiSpec {
    // 생성
    void createProductReview(ProductReviewRequest productReviewRequest, User user);

    // 수정
    void updateProductReview(ProductReviewModifyRequest ProductReviewModifyRequest);

    // 식제
    void deleteProductReview(Long id);

    // 단건 검색
    ResponseEntity<ProductReviewResponse> getProductReview(Long id);

    // 전체 검색
    ResponseEntity<List<ProductReviewResponse>> getProductReviewList(Long saleProductId);

}
