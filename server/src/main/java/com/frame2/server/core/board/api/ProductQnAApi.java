package com.frame2.server.core.board.api;


import com.frame2.server.core.board.application.ProductQnAdService;
import com.frame2.server.core.board.payload.SimpleProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.annotations.MemberOnly;
import com.frame2.server.core.support.request.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class ProductQnAApi implements ProductQnAApiSpec {

    private final ProductQnAdService productQnAService;

    // 질문 리스트 전체 조회
    @GetMapping
    public ResponseEntity<List<SimpleProductQnA>> qnaList() {
        ProductQnAListResponse productQnAListResponse = productQnAService.getProductQnAList();
        List<SimpleProductQnA> simpleProductQnAList = productQnAListResponse.simpleProductQnAList();
        return ResponseEntity.ok().body(simpleProductQnAList);
    }

    // 질문 단건 조회
    @GetMapping("/{productQnAId}")
    public ResponseEntity<ProductQnAResponse> getProductQnA(@PathVariable("productQnAId") Long id) {
        ProductQnAResponse productQnAResponse = productQnAService.getProductQnA(id);
        return ResponseEntity.ok().body(productQnAResponse);
    }

    // 질문 생성
    @MemberOnly
    @PostMapping
    public void createQuestion(@RequestBody ProductQnARegisterRequest productQnARequest, @Auth User user) {
        productQnAService.questionCreate(productQnARequest, user.id());
    }

    // 질문 수정
    @MemberOnly
    @PatchMapping
    public void update(@RequestBody ProductQnAModifyRequest productQnAModifyRequest) {
        productQnAService.questionModify(productQnAModifyRequest);
    }

    // 질문 삭제
    @MemberOnly
    @DeleteMapping("/{productQnAId}")
    public void delete(@PathVariable("productQnAId") Long id) {
        productQnAService.remove(id);
    }

    // 답변 생성 : 답변이란 엔티티가 따로 존재하지 않기 때문에
    // 기존의 qna객체를 업데이트한다.
    @MemberOnly
    @PatchMapping("/answer")
    public void answerCreate(@RequestBody ProductQnAAnswerRequest productQnAAnswerRequest) {
        productQnAService.answer(productQnAAnswerRequest);
    }

    // 답변 수정
    @MemberOnly
    @PatchMapping("/answer/{productQnAId}")
    public void answerUpdate(@RequestBody ProductQnAAnswerRequest productQnAAnswerRequest) {
        productQnAService.answerModify(productQnAAnswerRequest);
    }
}
