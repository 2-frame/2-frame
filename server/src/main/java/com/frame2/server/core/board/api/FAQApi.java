package com.frame2.server.core.board.api;

import com.frame2.server.core.board.application.FAQService;
import com.frame2.server.core.board.domain.FAQCategory;
import com.frame2.server.core.board.payload.response.FAQResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FAQApi implements FAQApiSpec {

    private final FAQService faqService;

    // 카테고리에 따른 전체 조회
    @GetMapping
    public ResponseEntity<List<FAQResponse>> getCategoryFAQList(
            @RequestParam(value = "category", required = false) FAQCategory category) {
        System.out.println("category = " + category);
        List<FAQResponse> faqList;

        if (category != null) {
            faqList = faqService.getCategoryFAQList(category);
        } else {
            faqList = faqService.getFAQList();
        }
        return ResponseEntity.ok(faqList);
    }

    //단건 조회
    @Override
    @GetMapping("/{faqId}")
    public ResponseEntity<FAQResponse> getFAQ(@PathVariable("faqId") Long faqId) {
        FAQResponse faqResponse = faqService.getFAQ(faqId);
        return ResponseEntity.ok(faqResponse);
    }
}
