package com.frame2.server.core.order.payload.response;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponse(

        Long orderId,
        LocalDateTime orderDate,
        String orderStatus,
        String mainProductName,
        int totalPrice
) {

}
