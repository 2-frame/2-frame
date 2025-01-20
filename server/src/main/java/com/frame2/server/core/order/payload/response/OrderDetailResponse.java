package com.frame2.server.core.order.payload.response;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record OrderDetailResponse(

        Long orderId,
        Long orderDetailId,
        String productName,
        int quantity,
        int price,
        String deliveryStatus,
        LocalDate deliveryStartDate,
        LocalDate deliveryEndDate,
        boolean exchangeReturnPossible,
        boolean exchangeReturnRequested
) {

}
