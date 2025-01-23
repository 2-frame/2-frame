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
@RequestMapping("/products/{productId}/qna")
public class ProductQnAApi implements ProductQnAApiSpec {

    private final ProductQnAdService productQnAService;

    // 한 상품에 대한 QnA 전체 조회
    @GetMapping
    public ResponseEntity<List<SimpleProductQnA>> qnaList(@PathVariable("productId") Long productId) {
        ProductQnAListResponse productQnAListResponse = productQnAService.getProductQnAList(productId);
        List<SimpleProductQnA> simpleProductQnAList = productQnAListResponse.simpleProductQnAList();
        return ResponseEntity.ok().body(simpleProductQnAList);
    }

    // 한 상품에 대한 질문 단건 조회
    @GetMapping("/{productQnAId}")
    public ResponseEntity<ProductQnAResponse> getProductQnA(
            @PathVariable("productId") Long productId,
            @PathVariable("productQnAId") Long productQnAId) {
        ProductQnAResponse productQnAResponse = productQnAService.getProductQnA(productId, productQnAId);
        return ResponseEntity.ok().body(productQnAResponse);
    }

    // 질문 생성
    @MemberOnly
    @PostMapping
    public void createQuestion(
            @RequestBody ProductQnARegisterRequest productQnARequest,
            @Auth User user,
            @PathVariable("productId") Long productId
    ) {
        productQnAService.questionCreate(productQnARequest, user.id(), productId);
    }

    // 질문 수정
    @MemberOnly
    @PatchMapping("/{productQnAId}") //products/1/qna/1
    public void update(@RequestBody ProductQnAModifyRequest productQnAModifyRequest,
                       @Auth User user,
                       @PathVariable("productId") Long productId,
                       @PathVariable("productQnAId") Long productQnAId) {
        productQnAService.questionModify(productQnAModifyRequest, productId, productQnAId);
    }

    // 질문 삭제
    // 실제 삭제가 아니기 때문에 patch 메서드 사용
    @MemberOnly
    @DeleteMapping("/{productQnAId}")
    public void delete(
            @PathVariable("productId") Long productId,
            @PathVariable("productQnAId") Long productQnAId,
            @Auth User user) {
        productQnAService.remove(productId, productQnAId);
    }

    // 답변 생성 : 답변이란 엔티티가 따로 존재하지 않기 때문에
    // 기존의 qna객체를 업데이트한다.
    @MemberOnly
    @PatchMapping("/{productQnAId}/answer")
    public void answerCreate(@RequestBody ProductQnAAnswerRequest productQnAAnswerRequest,
                             @Auth User user,
                             @PathVariable("productId") Long productId,
                             @PathVariable("productQnAId") Long productQnAId) {
        productQnAService.answer(productQnAAnswerRequest, productId, productQnAId);
    }

}
