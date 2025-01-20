package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Category;
import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponse(
        Long id,
        String categoryName,
        List<CategoryResponse> subCategories
)
{
    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .subCategories(
                        category.getSubCategories().stream()
                                .map(CategoryResponse::from)
                                .toList()
                )
                .build();
    }
}
