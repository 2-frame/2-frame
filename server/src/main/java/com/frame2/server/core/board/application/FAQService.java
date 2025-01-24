package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.FAQCategory;
import com.frame2.server.core.board.payload.response.FAQResponse;

import java.util.List;

public interface FAQService {

    //단건 조회
    FAQResponse getFAQ(Long faqId);

    List<FAQResponse> getFAQList();

    // 카테고리에 따른 전체 조회
    List<FAQResponse> getCategoryFAQList(FAQCategory category);
}
