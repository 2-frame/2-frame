package com.frame2.server.core.order.api;

import com.frame2.server.core.order.application.OrderService;
import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.support.annotations.MemberOnly;
import com.frame2.server.core.support.response.IdResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderApi implements OrderApiSpec{

    private final OrderService orderServiceImpl;

    @Override
    @MemberOnly
    @PostMapping
    public IdResponse createOrder(@RequestBody @Valid OrderCreateRequest request) {

        return orderServiceImpl.createOrder(request);
    }
}
