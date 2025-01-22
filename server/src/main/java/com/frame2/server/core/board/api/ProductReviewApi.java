package com.frame2.server.core.board.api;

import com.frame2.server.core.board.application.ProductReviewService;
import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.annotations.MemberOnly;
import com.frame2.server.core.support.request.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/{saleProductId}/reviews")
public class ProductReviewApi implements ProductReviewApiSpec {

    private final ProductReviewService productReviewService;

    // create
    @Override
    @MemberOnly
    @PostMapping
    public void createProductReview(
            @Valid @RequestBody ProductReviewRequest productReviewRequest,
            @Auth User user,
            @PathVariable("saleProductId") Long saleProductId
    ) {
        productReviewService.productReviewCreate(productReviewRequest, user.id(), saleProductId);
    }

    // update
    @Override
    @MemberOnly
    @PatchMapping("/{productReviewId}")
    public void updateProductReview(
            @Valid @RequestBody ProductReviewModifyRequest ProductReviewModifyRequest,
            @Auth User user,
            @PathVariable("productReviewId") Long productReviewId
    ) {
        productReviewService.productReviewModify(ProductReviewModifyRequest, productReviewId);
    }

    // delete
    @Override
    @MemberOnly
    @DeleteMapping("/{productReviewId}")
    public void deleteProductReview(@PathVariable("productReviewId") Long id, @Auth User user) {
        productReviewService.remove(id);
    }

    // 단건 조회
    @Override
    @GetMapping("/{productReviewId}")
    public ResponseEntity<ProductReviewResponse> getProductReview(@PathVariable("productReviewId") Long id) {
        ProductReviewResponse productReviewResponse = productReviewService.getProductReview(id);
        return ResponseEntity.ok().body(productReviewResponse);
    }

    // 한 상품에 대한 전체 리뷰 조회
    @Override
    @GetMapping
    public ResponseEntity<List<ProductReviewResponse>> getProductReviewList(
            @PathVariable("saleProductId") Long saleProductId) {
        List<ProductReviewResponse> productReviewList = productReviewService.getProductReviewList(saleProductId);
        return ResponseEntity.ok().body(productReviewList);
    }
}
