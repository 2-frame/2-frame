package com.frame2.server.core.board.api;

import brave.Response;
import com.frame2.server.core.board.application.ProductReviewService;
import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ProductReviewApi implements ProductReviewApiSpec{

    private final ProductReviewService productReviewService;

    @Override
    @PostMapping("/add")
    public void createProductReview(ProductReviewRequest productReviewRequest) {
        productReviewService.productReviewCreate(productReviewRequest);
    }

    @Override
    @PatchMapping("/{productReviewId")
    public void update(@RequestBody ProductReviewModifyRequest ProductReviewModifyRequest) {
        productReviewService.productReviewModify(ProductReviewModifyRequest);
    }

    @Override
    @DeleteMapping("/{productReviewId}")
    public void delete(@PathVariable("productReviewId") Long id) {
        productReviewService.remove(id);
    }

    @Override
    @GetMapping("/{productReviewId}")
    public ResponseEntity<ProductReviewResponse> getProductReview(@PathVariable("productReviewId") Long id) {
        ProductReviewResponse productReviewResponse = productReviewService.getProductReview(id);
        return ResponseEntity.ok().body(productReviewResponse);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductReviewResponse>> getProductReviewList() {
        List<ProductReviewResponse> productReviewList = productReviewService.getProductReviewList();
        return ResponseEntity.ok().body(productReviewList);
    }
}
