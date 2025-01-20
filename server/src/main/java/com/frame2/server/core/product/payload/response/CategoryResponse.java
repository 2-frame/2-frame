package com.frame2.server.core.product.payload.response;

import com.frame2.server.core.product.domain.Category;
import lombok.Builder;

import java.util.List;

public record CategoryResponse(
        Long id,
        String categoryName,
        List<CategoryResponse> subCategories
)
{
    @Builder
    public CategoryResponse{}

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
