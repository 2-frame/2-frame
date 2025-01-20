package com.frame2.server.core.product.api;

import com.frame2.server.core.product.application.CategoryService;
import com.frame2.server.core.product.payload.response.CategoryResponse;
import com.frame2.server.core.product.payload.response.ProductListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryApi implements CategoryApiSpec{

    private final CategoryService categoryServiceImpl;

    @Override
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<CategoryResponse> response = categoryServiceImpl.getAllCategories();
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("categoryId") Long Id){
        CategoryResponse response = categoryServiceImpl.getCategory(Id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductListResponse>> getProductsByCategory(@PathVariable("categoryId") Long id) {
        List<ProductListResponse> response = categoryServiceImpl.getProductsByCategory(id);
        return ResponseEntity.ok().body(response);
    }
}
