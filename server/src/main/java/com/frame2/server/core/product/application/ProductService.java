package com.frame2.server.core.product.application;

import com.frame2.server.core.product.payload.response.ProductListResponse;
import com.frame2.server.core.product.payload.response.ProductResponse;
import com.frame2.server.core.product.payload.response.ProductSearchResponse;

import java.util.List;

public interface ProductService {
    List<ProductListResponse> getAllProducts();
    ProductResponse getProduct(Long id);
    List<ProductSearchResponse> searchProduct(String keyword);
}
