package com.frame2.server.core.product.infrastructure;

import com.frame2.server.core.product.domain.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {
}
