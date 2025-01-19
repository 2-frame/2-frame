package com.frame2.server.core.board.api;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.payload.SimpleProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품QnA api")
public interface ProductQnAApiSpec {

    ResponseEntity<List<SimpleProductQnA>> qnaList();

    ResponseEntity<ProductQnAResponse> getProductQnA(@PathVariable Long id);

    void createQuestion(@RequestBody ProductQnARegisterRequest productQnARequest);

    void update(@RequestBody ProductQnAModifyRequest productQnAModifyRequest);

    void delete(@PathVariable Long id);

    void answerCreate(@RequestBody ProductQnAAnswerRequest productQnAAnswerRequest);

    void answerUpdate(@RequestBody ProductQnAAnswerRequest productQnAAnswerRequest);
}
