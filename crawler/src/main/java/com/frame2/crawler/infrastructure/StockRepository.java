package com.frame2.crawler.infrastructure;

import com.frame2.crawler.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
