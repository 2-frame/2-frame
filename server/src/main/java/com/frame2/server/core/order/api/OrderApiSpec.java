package com.frame2.server.core.order.api;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "주문 API")
public interface OrderApiSpec {
    public Long createOrder(OrderCreateRequest request);
}
