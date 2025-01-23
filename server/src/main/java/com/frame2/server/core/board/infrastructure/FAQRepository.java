package com.frame2.server.core.board.infrastructure;

import com.frame2.server.core.board.domain.FAQ;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FAQRepository extends JpaRepository<FAQ, Long> {

    default FAQ findOne(Long faqId) {
        return findById(faqId).orElseThrow(() -> new DomainException(ExceptionType.FAQ_NOT_FOUND));
    }

    @Override
    @Query("select faq from FAQ faq where faq.id =:faqId and faq.deleteStatus = false ")
    Optional<FAQ> findById(Long faqId);

    @Override
    @Query("select faq from FAQ faq where faq.deleteStatus = false ")
    List<FAQ> findAll();
}
