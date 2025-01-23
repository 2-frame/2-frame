package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.ProductReview;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    
    default ProductReview findOne(Long id) {
        return findById(id).orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_REVIEW_NOT_FOUND));
    }

    @Override
    @Query("SELECT pr FROM ProductReview pr WHERE pr.id = :id and pr.deleteStatus = false")
    Optional<ProductReview> findById(@Param("id") Long id);

    // deleteStatus가 false이고, saleProduct의 id가 주어진 값인 데이터를 조회
    @Query("SELECT pr FROM ProductReview pr WHERE pr.deleteStatus = false AND pr.saleProduct.id = :saleProductId")
    List<ProductReview> findAllBySaleProductId(@Param("saleProductId") Long saleProductId);
}
