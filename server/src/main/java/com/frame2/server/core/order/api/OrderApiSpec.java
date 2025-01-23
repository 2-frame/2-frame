package com.frame2.server.core.order.api;

import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.support.request.User;
import com.frame2.server.core.support.response.IdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.web.PagedModel;

import java.util.List;

@Tag(name = "주문 API")
public interface OrderApiSpec {

    public IdResponse createOrder(User user, OrderCreateRequest request);
    public OrderResponse getOrder(User user, Long orderId);
    public PagedModel<OrderResponse> getOrders(User user, int page, int pageSize);
    public OrderDetailResponse getOderDetail(User user, Long orderDetailId);
    public List<OrderDetailResponse> getOrderDetails(User user, Long orderId);
    public IdResponse cancelOrder(User user, Long orderId);
    public IdResponse cancelOrderDetail(User user, Long orderDetailId);
}
