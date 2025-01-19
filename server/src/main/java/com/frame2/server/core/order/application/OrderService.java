package com.frame2.server.core.order.application;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.support.response.IdResponse;

public interface OrderService {

    public IdResponse createOrder(OrderCreateRequest request);
}
