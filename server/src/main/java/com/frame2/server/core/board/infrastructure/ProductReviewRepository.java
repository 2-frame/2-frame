package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    default ProductReview findProductReview(Long id) {
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_REVIEW_NOT_FOUND));
    }
}
