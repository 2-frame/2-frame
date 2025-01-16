package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.infrastructure.ProductQnARepository;
import com.frame2.server.core.board.payload.request.ProductQnAModifyRequest;
import com.frame2.server.core.board.payload.request.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.response.ProductQnASearchAllResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductQnAServiceImplTest {

    @Autowired
    ProductQnAServiceImpl productQnAService;

    @Autowired
    ProductQnARepository repository;

    @BeforeEach
    public void 객체생성() {
        for (int i = 1; i <= 10; i++) {
            ProductQnARegisterRequest request = new ProductQnARegisterRequest("testUser" + i, "질문 있습니다." + i);
            ProductQnA productQnA = productQnAService.questionCreate(request);
        }
    }

    @Test
    @DisplayName("QnA 생성 테스트")
    public void create() {
        ProductQnARegisterRequest request = new ProductQnARegisterRequest("testUser", "질문 있습니다.");
        ProductQnA productQnA = productQnAService.questionCreate(request);

        ProductQnA productQnA1 = repository.findById(productQnA.getId()).orElseThrow();

        assertEquals(productQnA.getId(), productQnA1.getId());
    }

    @Test
    @DisplayName("QnA 수정 테스트")
    public void QnAUpdate() {

        ProductQnAModifyRequest request = new ProductQnAModifyRequest("test User", "Test Question");

        ProductQnA entity = request.toEntity();
        productQnAService.questionModify(request, 1L);

//        ProductQnASearchResponse productQnASearchResponse = productQnAService.searchOne(1L);
        ProductQnA productQnA = repository.findById(1L).orElseThrow();
        System.out.println("productQnA = " + productQnA);

    }


    @Test
    @DisplayName("QnA 조회 테스트")
    public void searchOneTest() {
        ProductQnA productQnA = productQnAService.searchOne(1L).productQnA();
        assertEquals(1L, productQnA.getId());
    }

    @Test
    @DisplayName("QnA 전체 조회 테스트")
    public void searchAllTest() {
        ProductQnASearchAllResponse productQnASearchAllResponse = productQnAService.searchAll();
        List<ProductQnA> list = productQnASearchAllResponse.list();

        assertEquals(10, list.size());
    }

//    @Test
//    @DisplayName("답변 생성")
//    public void answer(){
//        ProductQnAAnswerRegisterRequest request = new ProductQnAAnswerRegisterRequest(1L, "답변입니다.", "관리자");
//        ProductQnA answer = productQnAService.answer(request, request.id());
//
//        ProductQnASearchResponse productQnASearchResponse = productQnAService.searchOne(request.id());
//        ProductQnA productQnA = productQnASearchResponse.productQnA();
//        System.out.println("productQnA = " + productQnA);
//    }

}