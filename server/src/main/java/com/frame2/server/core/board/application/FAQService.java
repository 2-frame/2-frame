package com.frame2.server.core.board.application;

import com.frame2.server.core.board.payload.response.FAQResponse;

import java.util.List;

public interface FAQService {

    //단건 조회
    public FAQResponse getFAQ(Long faqId);

    public List<FAQResponse> getFAQList();
}
