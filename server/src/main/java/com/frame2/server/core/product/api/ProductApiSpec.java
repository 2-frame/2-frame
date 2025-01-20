package com.frame2.server.core.product.api;

import com.frame2.server.core.product.payload.response.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "상품 api")
public interface ProductApiSpec {

    ResponseEntity<List<ProductListResponse>> getAllProducts();

    ResponseEntity<ProductDetailResponse> getProduct(Long id);

    ResponseEntity<List<ProductSearchResponse>> searchProduct(String keyword);

    ResponseEntity<List<SaleProductListResponse>> getAllSaleProducts(int minPrice, int maxPrice, String sort);

    ResponseEntity<SaleProductDetailResponse> getSaleProduct(Long id);
}
