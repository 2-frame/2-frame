package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.AnswerStatus;
import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.infrastructure.ProductQnARepository;
import com.frame2.server.core.board.payload.SimpleProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;
import com.frame2.server.core.example.payload.SimpleExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductQnAServiceImpl implements ProductQnAdService{

    private final ProductQnARepository productQnARepository;

    // 질문 생성
    @Override
    public ProductQnA questionCreate(ProductQnARegisterRequest request) {
        ProductQnA productQnA = request.toEntity();
        return productQnARepository.save(productQnA);
    }

    // 질문 수정
    @Override
    public ProductQnA questionModify(ProductQnAModifyRequest request) {
        ProductQnA productQnA = productQnARepository.findbyidProductQnA(request.id());
        return productQnA.updateQuestion(request.title(), request.question());
    }

    // 답변 등록
    @Override
    public ProductQnA answer(ProductQnAAnswerRequest answerRegisterRequest) {
        ProductQnA requestProductQnA = answerRegisterRequest.toEntity();

        ProductQnA productQnA = productQnARepository.findbyidProductQnA(answerRegisterRequest.id());
        return productQnA.createAnswer(
                requestProductQnA.getAnswer(),
                requestProductQnA.getManager(),
                requestProductQnA.getAnswer_YN()
                );
    }

    // 답변 수정
    @Override
    public ProductQnA answerModify(ProductQnAAnswerRequest request) {
        ProductQnA productQnA = productQnARepository.findbyidProductQnA(request.id());
        return productQnA.updateAnswer(request.answer());
    }

    // 질문 삭제
    @Override
    public void remove(Long id) {
        ProductQnA productQnA = productQnARepository.findbyidProductQnA(id);
        productQnARepository.deleteById(productQnA.getId());
    }

    // 질문 단건 조회
    @Override
    @Transactional(readOnly = true)
    public ProductQnAResponse getProductQnA(Long id) {
        ProductQnA productQnA = productQnARepository.findbyidProductQnA(id);
        SimpleProductQnA simpleProductQnA = SimpleProductQnA.from(productQnA);
        return new ProductQnAResponse(simpleProductQnA);
    }

    // 질문 전체 조회
    @Override
    @Transactional(readOnly = true)
    public ProductQnAListResponse getProductQnAList() {
        List<ProductQnA> productQnAList = productQnARepository.findAll();
        return ProductQnAListResponse.of(productQnAList);
    }

}
