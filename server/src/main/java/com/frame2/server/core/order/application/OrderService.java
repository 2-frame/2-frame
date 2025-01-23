package com.frame2.server.core.order.application;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.support.response.IdResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface OrderService {

    public IdResponse createOrder(Long memberId, OrderCreateRequest request);
    public OrderResponse getOrder(Long memeberId, Long orderId);
    public PagedModel<OrderResponse> getOrders(Long memberId, Pageable pageable);
    public OrderDetailResponse getOderDetail(Long memeberId, Long orderDetailId);
    public List<OrderDetailResponse> getOrderDetails(Long memberId, Long orderId);
    public IdResponse cancelOrder(Long memberId, Long orderId);
    public IdResponse cancelOrderDetail(Long memberId, Long orderDetailId);
}
