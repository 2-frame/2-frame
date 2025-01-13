package com.frame2.server.core.example.service;

import com.frame2.server.core.example.payload.ExampleRegisterRequest;
import com.frame2.server.core.example.payload.ExampleSearchResponse;

public interface ExampleService {

    void create(ExampleRegisterRequest request);

    void modify();

    void remove();

    ExampleSearchResponse find();
}
