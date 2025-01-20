package com.frame2.server.core.order.payload.request;

import com.frame2.server.core.order.domain.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderCreateRequest(
        @NotNull
        Long memberId,

        @NotBlank(message = "받는 사람 이름은 필수입니다.")
        String recipient,

        @NotBlank(message = "받는 사람 연락처는 필수입니다.")
        String recipientContact,

        @NotBlank(message = "기본주소는 필수입니다.")
        String recipientAddress1,

        @NotBlank(message = "받는 사람 이름은 필수입니다.")
        String recipientAddress2,

        @Size(max = 50, message = "배송 요청사항은 최대 50글자까지 입력할 수 있습니다.")
        String deliveryRequest,

        List<OrderDetailRequest> orderDetails
) {
    public record OrderDetailRequest(

            @NotNull
            Long saleProductId,

            @NotNull(message = "상품 수량은 필수입니다.")
            @Min(value = 1, message = "상품 수량은 최소 1개 이상이어야 합니다.")
            int quantity
    ){ }
    public Order toEntity(){
        return Order.builder()
                .memberId(memberId)
                .recipient(recipient)
                .recipientContact(recipientContact)
                .recipientAddress1(recipientAddress1)
                .recipientAddress2(recipientAddress2)
                .deliveryRequest(deliveryRequest)
                .build();
    }
}
