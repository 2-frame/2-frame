package com.frame2.server.core.product.infrastructure;

import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {
    
    default SaleProduct findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_NOT_FOUND));
    }

    List<SaleProduct> findBySalePriceBetween(int minPrice, int maxPrice, Sort sort);
}
