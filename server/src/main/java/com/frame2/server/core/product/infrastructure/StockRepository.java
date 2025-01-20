package com.frame2.server.core.product.infrastructure;

import com.frame2.server.core.product.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findBySaleProductId(Long saleProductId);
}
