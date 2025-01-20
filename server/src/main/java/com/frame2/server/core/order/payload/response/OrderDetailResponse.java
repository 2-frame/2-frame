package com.frame2.server.core.order.payload.response;

import com.frame2.server.core.order.domain.OrderDetail;
import java.time.LocalDate;

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
    public static OrderDetailResponse from(OrderDetail orderDetail) {
        return new OrderDetailResponse(
                orderDetail.getOrder().getId(),
                orderDetail.getId(),
                orderDetail.getSaleProduct().getName(),
                orderDetail.getQuantity(),
                orderDetail.getPrice(),
                orderDetail.getDeliveryStatus().name(),
                orderDetail.getDeliveryStartDate(),
                orderDetail.getDeliveryEndDate(),
                orderDetail.isExchangeReturnPossible(),
                orderDetail.isExchaneReturnRequested()
        );
    }
}
