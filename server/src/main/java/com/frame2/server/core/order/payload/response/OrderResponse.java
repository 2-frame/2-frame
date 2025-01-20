package com.frame2.server.core.order.payload.response;


import com.frame2.server.core.order.domain.Order;
import java.time.LocalDateTime;

public record OrderResponse(

        Long orderId,
        LocalDateTime orderDate,
        String orderStatus,
        String mainProductName,
        int totalPrice
) {

    public static OrderResponse from(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getOrderStatus().name(),
                order.getMainProductName(),
                order.getTotalPrice()
        );
    }
}
