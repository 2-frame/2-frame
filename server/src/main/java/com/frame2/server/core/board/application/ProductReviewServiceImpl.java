package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.board.infrastructure.ProductReviewRepository;
import com.frame2.server.core.board.payload.request.ProductReviewModifyRequest;
import com.frame2.server.core.board.payload.request.ProductReviewRequest;
import com.frame2.server.core.board.payload.response.ProductReviewListResponse;
import com.frame2.server.core.board.payload.response.ProductReviewResponse;
import com.frame2.server.core.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService{

    private final ProductReviewRepository productReviewRepository;

    @Override
    public ProductReview productReviewCreate(ProductReviewRequest request) {
        ProductReview productReview = request.toEntity();
        return productReviewRepository.save(productReview);
    }

    @Override
    public ProductReview productReviewModify(ProductReviewModifyRequest request) {
        ProductReview productReview = request.toEntity();
        ProductReview findProductReview = productReviewRepository.findProductReview(request.id());
        return findProductReview.updateProductReview(productReview);
    }

    @Override
    public void remove(Long id) {
        productReviewRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductReviewResponse getProductReview(Long id) {
        ProductReview productReview = productReviewRepository.findProductReview(id);
        return ProductReviewResponse.from(productReview);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewResponse> getProductReviewList() {
        List<ProductReview> productReviewList = productReviewRepository.findAll();
        return productReviewList.stream()
                .map(ProductReviewResponse::from)
                .toList();
    }
}
