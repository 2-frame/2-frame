package com.frame2.crawler.infrastructure;

import com.frame2.crawler.domain.SaleProduct;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {

    default SaleProduct findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("엔티티가 없어요"));
    }

    List<SaleProduct> findBySalePriceBetween(int minPrice, int maxPrice, Sort sort);
}
