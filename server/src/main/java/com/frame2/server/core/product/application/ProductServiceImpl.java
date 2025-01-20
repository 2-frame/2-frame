package com.frame2.server.core.product.application;

import com.frame2.server.core.product.domain.Product;
import com.frame2.server.core.product.infrastructure.ProductRepository;
import com.frame2.server.core.product.payload.response.ProductListResponse;
import com.frame2.server.core.product.payload.response.ProductResponse;
import com.frame2.server.core.product.payload.response.ProductSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductListResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductListResponse::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findOne(id);
        return ProductResponse.from(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSearchResponse> searchProduct(String keyword) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);

        return products.stream()
                .map(ProductSearchResponse::from)
                .toList();
    }
}
