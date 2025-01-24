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
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductQnAServiceImplTest {

    @Autowired
    ProductQnAServiceImpl productQnAService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductQnARepository productQnARepository;

    @BeforeEach
    public void 객체생성() {
        Member member = null;

        for (int i = 1; i <= 10; i++) {
            member = new Member("member" + i, "nickName" + i, "mail" + i);
            memberRepository.save(member);
        }

        Product product = new Product();
        productRepository.save(product);


        for (int i = 1; i <= 10; i++) {
            ProductQnARegisterRequest request = new ProductQnARegisterRequest(
                    product.getId(), "test Title" + i, "test Question" + i);
            ProductQnA productQnA = productQnAService.questionCreate(request, member.getId());
        }
    }

    @Test
    @DisplayName("QnA 생성 테스트")
    public void create() {
        Member member = new Member("member", "nickName", "mail");
        memberRepository.save(member);

        Product product = new Product();
        productRepository.save(product);

        ProductQnARegisterRequest request = new ProductQnARegisterRequest(
                product.getId(), "test Title", "test Question");
        ProductQnA productQnA = productQnAService.questionCreate(request, member.getId());

        ProductQnA productQnA1 = productQnARepository.findById(productQnA.getId()).orElseThrow();

        assertEquals(productQnA.getId(), productQnA1.getId());
    }

    @Test
    @DisplayName("QnA 수정 테스트")
    public void QnAUpdate() {
        ProductQnAModifyRequest request = new ProductQnAModifyRequest("title 메롱", "question");
        ProductQnA modifyProductQnA = productQnAService.questionModify(request, 1L);
        ProductQnA productQnA = productQnARepository.findOne(1L);

        assertEquals(modifyProductQnA.getQuestion(), productQnA.getQuestion());
        assertEquals(modifyProductQnA.getTitle(), productQnA.getTitle());
    }

    @Test
    @Order(2)
    @DisplayName("QnA 조회 테스트")
    public void getProductQnATest() {
        SimpleProductQnA simpleProductQnA = productQnAService.getProductQnA(1L).simpleProductQnA();

        assertEquals(1L, simpleProductQnA.id());
    }

    @Test
    @Order(1)
    @DisplayName("QnA 전체 조회 테스트")
    public void getProductQnAListTest() {
        ProductQnAListResponse productQnAListResponse = productQnAService.getProductQnAList();
        List<SimpleProductQnA> list = productQnAListResponse.simpleProductQnAList();

        assertEquals(10, list.size());
    }

    @Test
    @DisplayName("답변 생성")
    public void answer() {
        ProductQnAAnswerRequest request = new ProductQnAAnswerRequest(1L, "답변입니다.", "관리자");
        ProductQnA answer = productQnAService.answer(request);

        ProductQnAResponse findProductQnA = productQnAService.getProductQnA(answer.getId());
        SimpleProductQnA simpleProductQnA = findProductQnA.simpleProductQnA();

        assertEquals(answer.getAnswer(), simpleProductQnA.answer());
/ /        assertEquals(answer., simpleProductQnA.answerYN());


    }
}