package com.frame2.server.core.board.application;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.example.payload.ExampleRegisterRequest;

import java.util.List;

public class ProductQnAServiceImpl implements BoardService{

    @Override
    public ProductQnA create(ExampleRegisterRequest request) {
        return ;
    }

    @Override
    public ProductQnA modify() {
        return null;
    }

    @Override
    public void remove() {

    }

    @Override
    public Object searchOne() {
        return null;
    }

    @Override
    public List<Object> searchAll() {
        return List.of();
    }
}
