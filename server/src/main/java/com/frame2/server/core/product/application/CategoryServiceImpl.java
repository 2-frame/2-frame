package com.frame2.server.core.product.application;

import com.frame2.server.core.product.domain.Category;
import com.frame2.server.core.product.domain.Product;
import com.frame2.server.core.product.infrastructure.CategoryRepository;
import com.frame2.server.core.product.infrastructure.ProductRepository;
import com.frame2.server.core.product.payload.response.CategoryResponse;
import com.frame2.server.core.product.payload.response.ProductListResponse;
import com.frame2.server.core.product.payload.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        List<Category> rootCategories = categoryRepository.findAllByParentCategoryIsNull();
        return rootCategories.stream()
                .map(CategoryResponse::from)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategory(Long id) {
        Category category = categoryRepository.findOne(id);
        return CategoryResponse.from(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListResponse> getProductsByCategory(Long id){
        categoryRepository.findOne(id);
        List<Product> products = productRepository.findByCategoryIdIn(id);
        return products.stream()
                .map(ProductListResponse::from)
                .toList();
    }
}
