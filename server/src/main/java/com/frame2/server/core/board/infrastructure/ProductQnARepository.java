package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQnARepository extends JpaRepository<ProductQnA, Long> {
    default ProductQnA findbyidProductQnA(Long id){
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_QNA_NOT_FOUND));
    }
}
