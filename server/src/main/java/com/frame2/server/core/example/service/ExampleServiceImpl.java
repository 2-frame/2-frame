package com.frame2.server.core.example.service;

import com.frame2.server.core.example.payload.ExampleRegisterRequest;
import com.frame2.server.core.example.payload.ExampleSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

    @Override
    public void create(ExampleRegisterRequest request) {
        exampleRepository.save(request.toEntity());
    }

    @Override
    public void modify() {

    }

    @Override
    public void remove() {

    }

    @Override
    @Transactional(readOnly = true)
    public ExampleSearchResponse find() {
        return ExampleSearchResponse.of(
                exampleRepository.findAll()
        );
    }
}
