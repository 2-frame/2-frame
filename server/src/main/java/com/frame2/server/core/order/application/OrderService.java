package com.frame2.server.core.order.application;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.support.response.IdResponse;

import java.util.List;

public interface OrderService {

    public IdResponse createOrder(OrderCreateRequest request);
    public OrderResponse getOrder(Long orderId);
    public List<OrderResponse> getOrders(Long memberId);
    public OrderDetailResponse getOderDetail(Long orderDetailId);
    public List<OrderDetailResponse> getOrderDetails(Long orderId);
}
