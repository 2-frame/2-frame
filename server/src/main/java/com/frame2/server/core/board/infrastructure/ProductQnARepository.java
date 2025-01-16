package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.ProductQnA;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductQnARepository extends JpaRepository<ProductQnA, Long> {

}
