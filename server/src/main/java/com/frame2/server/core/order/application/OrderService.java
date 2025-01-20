package com.frame2.server.core.order.application;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.support.response.IdResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface OrderService {

    public IdResponse createOrder(OrderCreateRequest request);
    public OrderResponse getOrder(Long orderId);
    public PagedModel<OrderResponse> getOrders(Long memberId, Pageable pageable);
    public OrderDetailResponse getOderDetail(Long orderDetailId);
    public PagedModel<OrderDetailResponse> getOrderDetails(Long orderId, Pageable pageable);
}
