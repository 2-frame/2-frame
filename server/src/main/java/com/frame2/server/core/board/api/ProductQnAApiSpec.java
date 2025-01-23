package com.frame2.server.core.board.api;

import com.frame2.server.core.board.payload.SimpleProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;
import com.frame2.server.core.support.request.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "상품QnA api")
public interface ProductQnAApiSpec {

    ResponseEntity<List<SimpleProductQnA>> qnaList(Long productId);

    ResponseEntity<ProductQnAResponse> getProductQnA(Long productId, Long id);

    void createQuestion(ProductQnARegisterRequest productQnARequest, User user, Long productId);

    void update(ProductQnAModifyRequest productQnAModifyRequest, User user, Long productId, Long productQnAId);

    void delete(Long productId, Long id, User user);

    void answerCreate(ProductQnAAnswerRequest productQnAAnswerRequest, User user, Long productId, Long productQnAId);

}
