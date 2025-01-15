package com.frame2.server.core.board.application;

import com.frame2.server.core.example.payload.ExampleRegisterRequest;
import com.frame2.server.core.example.payload.ExampleSearchResponse;

import java.util.List;

public interface BoardService {
    Object create(ExampleRegisterRequest request);

    Object modify();

    void remove();

//    BoardSearchResponse find();
    Object searchOne();

    List<Object> searchAll();
}
