package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.board.infrastructure.ProductQnARepository;
import com.frame2.server.core.board.payload.ProductQnARegisterRequest;
import com.frame2.server.core.board.payload.ProductQnASearchResponse;
import com.frame2.server.core.example.payload.ExampleRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductQnAServiceImpl implements ProductQnAdService{

    private final ProductQnARepository productQnARepository;

    
    // 질문
    @Override
    public ProductQnA questionCreate(ProductQnARegisterRequest request) {
        ProductQnA productQnA = request.toEntity();
        return productQnARepository.save(productQnA);
    }

    // 질문 수정
    @Override
    public ProductQnA questionModify(ProductQnARegisterRequest request) {
        ProductQnA qnA = productQnARepository.findById(request.id()).orElseThrow();
        return null;
    }

    @Override
    public ProductQnA answer() {
        return null;
    }

    @Override
    public void remove() {

    }

    @Override
    public ProductQnASearchResponse searchOne() {
        return null;
    }

    @Override
    public List<ProductQnA> searchAll() {
        return List.of();
    }
}
