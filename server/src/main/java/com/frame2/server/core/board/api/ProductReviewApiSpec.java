package com.frame2.server.core.board.api;

import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;
import jdk.jfr.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Category("상품 리뷰 api")
public interface ProductReviewApiSpec {
    // 생성
    void createProductReview(@RequestBody ProductReviewRequest productReviewRequest);

    // 수정
    void update(@RequestBody ProductReviewModifyRequest ProductReviewModifyRequest);

    // 식제
    void delete(@PathVariable Long id);

    // 단건 검색
    ResponseEntity<ProductReviewResponse> getProductReview(@PathVariable Long id);

    // 전체 검색
    ResponseEntity<List<ProductReviewResponse>> getProductReviewList();

}
