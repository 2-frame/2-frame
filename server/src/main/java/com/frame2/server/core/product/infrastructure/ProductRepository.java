package com.frame2.server.core.product.infrastructure;

import com.frame2.server.core.product.domain.Product;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_NOT_FOUND));
    }
    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryId(Long id);
}
