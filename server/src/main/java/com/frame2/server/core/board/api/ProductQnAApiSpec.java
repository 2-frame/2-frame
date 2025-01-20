package com.frame2.server.core.board.api;

import com.frame2.server.core.board.payload.SimpleProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "상품QnA api")
public interface ProductQnAApiSpec {

    ResponseEntity<List<SimpleProductQnA>> qnaList();

    ResponseEntity<ProductQnAResponse> getProductQnA(Long id);

    void createQuestion(ProductQnARegisterRequest productQnARequest);

    void update(ProductQnAModifyRequest productQnAModifyRequest);

    void delete(Long id);

    void answerCreate(ProductQnAAnswerRequest productQnAAnswerRequest);

    void answerUpdate(ProductQnAAnswerRequest productQnAAnswerRequest);
}
