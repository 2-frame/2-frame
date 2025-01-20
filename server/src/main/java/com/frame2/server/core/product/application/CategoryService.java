package com.frame2.server.core.product.application;

import com.frame2.server.core.product.payload.response.CategoryResponse;
import com.frame2.server.core.product.payload.response.ProductListResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategory(Long id);

    List<ProductListResponse> getProductsByCategory(Long id);
}
