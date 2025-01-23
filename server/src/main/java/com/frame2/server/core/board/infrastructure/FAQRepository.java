package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
}
