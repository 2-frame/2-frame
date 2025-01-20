package com.frame2.server.core.product.api;

import com.frame2.server.core.product.payload.response.CategoryResponse;
import com.frame2.server.core.product.payload.response.ProductListResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "카테고리 api")
public interface CategoryApiSpec {

    ResponseEntity<List<CategoryResponse>> getAllCategories();

    ResponseEntity<CategoryResponse> getCategory(Long id);
}
