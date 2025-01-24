package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductQnARepository extends JpaRepository<ProductQnA, Long> {

    default ProductQnA findOne(Long productId, Long productQnAId) {
        return findByProductIdAndIdAndDeleteStatusIsFalse(productId, productQnAId)
                .orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_QNA_NOT_FOUND));
    }

    Optional<ProductQnA> findByProductIdAndIdAndDeleteStatusIsFalse(Long productId, Long productQnAId);

    List<ProductQnA> findByProductIdAndDeleteStatusFalse(Long productId);
}
