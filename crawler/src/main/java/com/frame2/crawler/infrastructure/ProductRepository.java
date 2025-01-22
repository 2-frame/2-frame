package com.frame2.crawler.infrastructure;

import com.frame2.crawler.domain.Product;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("엔티티가 없어요"));
    }

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryId(Long id);
}
