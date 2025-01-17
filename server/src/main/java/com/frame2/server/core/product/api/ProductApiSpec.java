package com.frame2.server.core.product.api;

import com.frame2.server.core.product.payload.response.ProductListResponse;
import com.frame2.server.core.product.payload.response.ProductResponse;
import com.frame2.server.core.product.payload.response.ProductSearchResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "상품 api")
public interface ProductApiSpec {

    ResponseEntity<List<ProductListResponse>> getAllProducts();

    ResponseEntity<ProductResponse> getProduct(@PathVariable("") Long id);

    ResponseEntity<List<ProductSearchResponse>> searchProduct(@RequestParam(value = "") String keyword);
}
