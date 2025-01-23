package com.frame2.server.core.board.payload.response;

import com.frame2.server.core.board.domain.FAQ;
import com.frame2.server.core.board.domain.FAQCategory;

public record FAQResponse(
        Long id,
        FAQCategory category,
        int order,
        String question,
        String answer
) {
    public static FAQResponse from(FAQ faq) {
        return new FAQResponse(
                faq.getId(),
                faq.getCategory(),
                faq.getOrder(),
                faq.getQuestion(),
                faq.getAnswer()
        );
    }
}
