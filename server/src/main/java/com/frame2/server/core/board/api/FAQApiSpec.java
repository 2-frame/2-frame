package com.frame2.server.core.board.api;

import com.frame2.server.core.board.domain.FAQCategory;
import com.frame2.server.core.board.payload.response.FAQResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "FAQ")
public interface FAQApiSpec {

    // 전체조회
    ResponseEntity<List<FAQResponse>> getFAQList();

    // 카테고리에 따른 전체 조회
    ResponseEntity<List<FAQResponse>> getCategoryFAQList(FAQCategory category);

    // 단건조회
    ResponseEntity<FAQResponse> getFAQ(Long feqId);
    
}

