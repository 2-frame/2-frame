package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.FAQ;
import com.frame2.server.core.board.infrastructure.FAQRepository;
import com.frame2.server.core.board.payload.response.FAQResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;

    @Transactional
    public FAQResponse getFAQ(Long faqId) {
        FAQ faq = faqRepository.findOne(faqId);
        return FAQResponse.from(faq);
    }

    @Transactional(readOnly = true)
    public List<FAQResponse> getFAQList() {
        return faqRepository.findAll()
                .stream()
                .map(FAQResponse::from)
                .toList();
    }
}
