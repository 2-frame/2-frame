package com.frame2.server.core.order.application;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;

public interface OrderService {

    public Long createOrder(OrderCreateRequest request);
}
