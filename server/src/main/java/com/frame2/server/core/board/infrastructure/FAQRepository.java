package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.FAQ;
import com.frame2.server.core.board.domain.FAQCategory;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FAQRepository extends JpaRepository<FAQ, Long> {

    default FAQ findOne(Long faqId) {
        return findByIdAndDeleteStatusFalse(faqId).orElseThrow(() -> new DomainException(ExceptionType.FAQ_NOT_FOUND));
    }

    Optional<FAQ> findByIdAndDeleteStatusFalse(Long id);

    List<FAQ> findAllByDeleteStatusFalse();

    List<FAQ> findByCategoryAndDeleteStatusFalse(FAQCategory category);
}
