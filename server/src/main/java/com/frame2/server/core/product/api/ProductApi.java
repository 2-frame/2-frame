package com.frame2.server.core.product.api;

import com.frame2.server.core.product.application.ProductService;
import com.frame2.server.core.product.payload.response.ProductListResponse;
import com.frame2.server.core.product.payload.response.ProductResponse;
import com.frame2.server.core.product.payload.response.ProductSearchResponse;
import com.frame2.server.core.support.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApi implements ProductApiSpec {

    private final ProductService productServiceImpl;

    @Override
    @GetMapping
    public ResponseEntity<List<ProductListResponse>> getAllProducts(){
        List<ProductListResponse> response = productServiceImpl.getAllProducts();
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") Long id){
        ProductResponse response = productServiceImpl.getProduct(id);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<ProductSearchResponse>> searchProduct(@RequestParam(value = "keyword",
            required = false, defaultValue = "") String keyword) {
        List<ProductSearchResponse> response = productServiceImpl.searchProduct(keyword);
        return ResponseEntity.ok().body(response);
    }
}
