package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductQnARepository extends JpaRepository<ProductQnA, Long> {

    default ProductQnA findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_QNA_NOT_FOUND));
    }

    @Override
    @Query("select prQnA from ProductQnA prQnA where prQnA.id =:id and prQnA.deleteStatus = false ")
    Optional<ProductQnA> findById(@Param("id") Long id);

    @Override
    @Query("select prQnA from ProductQnA prQnA where prQnA.deleteStatus = false ")
    List<ProductQnA> findAll();
}
