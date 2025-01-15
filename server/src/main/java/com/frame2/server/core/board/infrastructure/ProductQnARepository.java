package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.ProductQnA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQnARepository extends JpaRepository<ProductQnA, Long> {
}
