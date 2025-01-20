package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Option;
import lombok.Builder;

@Builder
public record OptionResponse(
    Long optionId,
    String name,
    String value,
    int additionalPrice
) {
    public static OptionResponse from(Option option) {
        if (option == null) return null;
        return OptionResponse.builder()
                .optionId(option.getId())
                .name(option.getName())
                .value(option.getValue())
                .additionalPrice(option.getAdditionalPrice())
                .build();
    }
}