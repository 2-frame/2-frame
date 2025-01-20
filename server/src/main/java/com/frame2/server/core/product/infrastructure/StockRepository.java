package com.frame2.server.core.product.infrastructure;

import com.frame2.server.core.product.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findBySaleProductId(Long id);
}
