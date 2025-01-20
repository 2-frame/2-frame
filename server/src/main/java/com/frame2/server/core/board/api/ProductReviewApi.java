package com.frame2.server.core.board.api;

import com.frame2.server.core.board.application.ProductReviewService;
import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.annotations.MemberOnly;
import com.frame2.server.core.support.request.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ProductReviewApi implements ProductReviewApiSpec {

    private final ProductReviewService productReviewService;

    // create
    @Override
//    @MemberOnly
    @PostMapping
    public void createProductReview(ProductReviewRequest productReviewRequest, @Auth User user) {
        productReviewService.productReviewCreate(productReviewRequest, user.id());
    }

    // update
    @Override
    @MemberOnly
    @PatchMapping("/{productReviewId}")
    public void update(@RequestBody ProductReviewModifyRequest ProductReviewModifyRequest) {
        productReviewService.productReviewModify(ProductReviewModifyRequest);
    }

    @Override
    @MemberOnly
    @DeleteMapping("/{productReviewId}")
    public void delete(@PathVariable("productReviewId") Long id) {
        productReviewService.remove(id);
    }

    // 단건 조회
    @Override
    @GetMapping("/{productReviewId}")
    public ResponseEntity<ProductReviewResponse> getProductReview(@PathVariable("productReviewId") Long id) {
        ProductReviewResponse productReviewResponse = productReviewService.getProductReview(id);
        return ResponseEntity.ok().body(productReviewResponse);
    }

    // 전체 조회
    @Override
    @GetMapping
    public ResponseEntity<List<ProductReviewResponse>> getProductReviewList() {
        List<ProductReviewResponse> productReviewList = productReviewService.getProductReviewList();
        return ResponseEntity.ok().body(productReviewList);
    }
}
