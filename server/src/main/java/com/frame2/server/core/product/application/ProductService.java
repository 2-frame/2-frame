package com.frame2.server.core.product.application;

import com.frame2.server.core.product.payload.response.*;

import java.util.List;

public interface ProductService {

    List<ProductListResponse> getAllProducts();

    ProductDetailResponse getProduct(Long id);

    List<ProductSearchResponse> searchProduct(String keyword);

    List<SaleProductListResponse> getAllSaleProducts(int minPrice, int maxPrice, String sort);

    SaleProductDetailResponse getSaleProduct(Long id);
}
