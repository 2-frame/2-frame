package com.frame2.server.core.order.api;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.support.response.IdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.web.PagedModel;

import java.util.List;

@Tag(name = "주문 API")
public interface OrderApiSpec {

    public IdResponse createOrder(OrderCreateRequest request);
    public OrderResponse getOrder(Long orderId);
    public PagedModel<OrderResponse> getOrders(Long memberId, int page, int pageSize);
    public OrderDetailResponse getOderDetail(Long orderDetailId);
    public PagedModel<OrderDetailResponse> getOrderDetails(Long orderId, int page, int pageSize);
}
