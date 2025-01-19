package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Option;
import lombok.Builder;

public record OptionResponse(
    Long optionId,
    String name,
    String value,
    int additionalPrice
) {
    @Builder
    public OptionResponse {}

    public static OptionResponse from(Option o) {
        if (o == null) return null;
        return OptionResponse.builder()
                .optionId(o.getId())
                .name(o.getName())
                .value(o.getValue())
                .additionalPrice(o.getAdditionalPrice())
                .build();
    }
}