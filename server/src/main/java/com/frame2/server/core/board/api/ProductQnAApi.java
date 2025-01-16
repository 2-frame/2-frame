package com.frame2.server.core.board.api;


import com.frame2.server.core.board.application.ProductQnAdService;
import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRegisterRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnASearchAllResponse;
import com.frame2.server.core.board.payload.response.ProductQnASearchResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class ProductQnAApi {

    @Autowired
    ProductQnAdService productQnAService;

    // 질문 리스트 전체 조회
    @GetMapping
    public ProductQnASearchAllResponse qnaList(){
        return productQnAService.searchAll();
    }

    // 질문 단건 조회
    @GetMapping("/search/{id}")
    public ProductQnASearchResponse qnaSearchOne(@PathVariable Long id) {
        return productQnAService.searchOne(id);
    }

    // 질문 생성
    @PostMapping("/add")
    public void createQuestion(ProductQnARegisterRequest productRequest,
                               HttpServletRequest request,
                               HttpServletResponse response
    ) throws IOException {

        String requestURI = request.getRequestURI();
        ProductQnA productQnA = productQnAService.questionCreate(productRequest);

        if (productQnA == null) {
           response.sendRedirect(requestURI);
        }
    }

    // 질문 수정
    @PatchMapping("/update/{id}")
    public void update( @PathVariable Long id,
                        ProductQnAModifyRequest productRequest,
                        HttpServletRequest request,
                        HttpServletResponse response
    ) throws IOException {

        String requestURI = request.getRequestURI();
        ProductQnA productQnA = productQnAService.questionModify(productRequest, id);

        if (productQnA == null){
            response.sendRedirect(requestURI);
        }

    }

    // 질문 삭제
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        productQnAService.remove(id);
    }

    // 답변 생성 : 답변이란 엔티티가 따로 존재하지 않기 때문에
    // 기존의 qna객체를 업데이트한다.
    @PatchMapping("/answer-add")
    public void answerAdd(ProductQnAAnswerRegisterRequest productRequest,
                          HttpServletRequest request,
                          HttpServletResponse response
    ) {
        productQnAService.answer(productRequest, productRequest.toEntity().getId());
    }
}
