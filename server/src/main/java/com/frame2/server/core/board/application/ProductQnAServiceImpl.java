package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.infrastructure.ProductQnARepository;
import com.frame2.server.core.board.payload.SimpleProductQnA;
import com.frame2.server.core.board.payload.request.ProductQnAAnswerRequest;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnAListResponse;
import com.frame2.server.core.board.payload.response.ProductQnAResponse;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.product.domain.Product;
import com.frame2.server.core.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductQnAServiceImpl implements ProductQnAdService {

    private final ProductQnARepository productQnARepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 질문 생성
    @Override
    public ProductQnA questionCreate(ProductQnARegisterRequest request, Long memberId, Long productId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Product product = productRepository.findOne(productId);

        ProductQnA productQnA = request.toEntity(member, product);
        return productQnARepository.save(productQnA);
    }

    // 질문 수정
    @Override
    public ProductQnA questionModify(ProductQnAModifyRequest request, Long productQnAId) {
        ProductQnA productQnA = productQnARepository.findOne(productQnAId);
        return productQnA.updateQuestion(request.title(), request.question());
    }

    // 답변 등록
    @Override
    public ProductQnA answer(ProductQnAAnswerRequest answerRegisterRequest, Long productQnAId) {
        ProductQnA requestProductQnA = answerRegisterRequest.toEntity();

        ProductQnA productQnA = productQnARepository.findOne(productQnAId);
        return productQnA.createAnswer(
                requestProductQnA.getAnswer(),
                requestProductQnA.getManager(),
                true,
                requestProductQnA.getAnswerDate()
        );
    }

    // 질문 삭제
    @Override
    public void remove(Long id) {
        productQnARepository.findOne(id).delete();
    }

    // 질문 단건 조회
    @Override
    @Transactional(readOnly = true)
    public ProductQnAResponse getProductQnA(Long id) {
        ProductQnA productQnA = productQnARepository.findOne(id);
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
