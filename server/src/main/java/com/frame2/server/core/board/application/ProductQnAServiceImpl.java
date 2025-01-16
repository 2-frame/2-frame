package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.infrastructure.ProductQnARepository;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRegisterRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.response.ProductQnASearchAllResponse;
import com.frame2.server.core.board.payload.response.ProductQnASearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductQnAServiceImpl implements ProductQnAdService{

    private final ProductQnARepository productQnARepository;

    // 질문 등록
    @Override
    public ProductQnA questionCreate(ProductQnARegisterRequest request) {
        ProductQnA productQnA = request.toEntity();
        return productQnARepository.save(productQnA);
    }

    // 질문 수정
    @Override
    public ProductQnA questionModify(ProductQnAModifyRequest request, Long id) {
        ProductQnA entity = request.toEntity();
        ProductQnA productQnA = productQnARepository.findById(id).orElseThrow();

        productQnA = ProductQnA.builder()
                .question(entity.getQuestion())
                .user_id(entity.getUser_id())
                .build();
        return productQnARepository.save(productQnA);
    }

    // 답변 등록
    @Override
    @Transactional
    public ProductQnA answer(ProductQnAAnswerRegisterRequest answerRegisterRequest, Long id) {

        ProductQnA requestProductQnA = answerRegisterRequest.toEntity();
        ProductQnA productQnA = productQnARepository.findById(answerRegisterRequest.id()).orElseThrow();


        System.out.println("productQnA = " + productQnA.getAnswer());

        productQnA = ProductQnA.builder()
                .answer(requestProductQnA.getAnswer())
                .manager(requestProductQnA.getManager())
                .answer_YN(true)
                .build();


        System.out.println("productQnA.getAnswer() = " + productQnA.getAnswer());
        System.out.println("productQnA = " + productQnA);

        return productQnARepository.save(productQnA);
    }
    
    // 질문 삭제
    @Override
    public void remove(Long id) {
        ProductQnA productQnA = productQnARepository.findById(id).orElseThrow();
        productQnARepository.deleteById(productQnA.getId());
    }

    // 질문 단건 조회
    @Override
    public ProductQnASearchResponse searchOne(Long id) {
        ProductQnA productQnA = productQnARepository.findById(id).orElseThrow();
        return new ProductQnASearchResponse(productQnA);
    }

    // 질문 전체 조회
    @Override
    public ProductQnASearchAllResponse searchAll() {
        List<ProductQnA> productQnAList = productQnARepository.findAll();
        return ProductQnASearchAllResponse.from(productQnAList);
    }

}
