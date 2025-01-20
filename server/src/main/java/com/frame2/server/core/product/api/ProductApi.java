package com.frame2.server.core.product.api;

import com.frame2.server.core.product.application.ProductService;
import com.frame2.server.core.product.payload.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProductDetailResponse> getProduct(@PathVariable("productId") Long id){
        ProductDetailResponse response = productServiceImpl.getProduct(id);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<ProductSearchResponse>> searchProduct(@RequestParam(value = "keyword",
            required = false, defaultValue = "") String keyword) {
        List<ProductSearchResponse> response = productServiceImpl.searchProduct(keyword);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/sales")
    public ResponseEntity<List<SaleProductListResponse>> getAllSaleProducts(
            @RequestParam(defaultValue = "0") int minPrice,
            @RequestParam(defaultValue = "999999999") int maxPrice,
            @RequestParam(defaultValue = "recentDesc") String sort) {
        List<SaleProductListResponse> response = productServiceImpl.getAllSaleProducts(minPrice, maxPrice, sort);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/sales/{saleProductId}")
    public ResponseEntity<SaleProductDetailResponse> getSaleProduct(@PathVariable("saleProductId") Long id) {
        SaleProductDetailResponse response = productServiceImpl.getSaleProduct(id);
        return ResponseEntity.ok().body(response);
    }
}
