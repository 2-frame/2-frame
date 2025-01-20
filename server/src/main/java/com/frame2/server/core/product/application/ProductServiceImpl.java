package com.frame2.server.core.product.application;

import com.frame2.server.core.product.domain.Product;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.product.domain.Stock;
import com.frame2.server.core.product.infrastructure.ProductRepository;
import com.frame2.server.core.product.infrastructure.SaleProductRepository;
import com.frame2.server.core.product.infrastructure.StockRepository;
import com.frame2.server.core.product.payload.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final SaleProductRepository saleProductRepository;
    private final StockRepository stockRepository;

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
    public ProductDetailResponse getProduct(Long id) {
        Product product = productRepository.findOne(id);
        return ProductDetailResponse.from(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSearchResponse> searchProduct(String keyword) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);

        return products.stream()
                .map(ProductSearchResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SaleProductListResponse> getAllSaleProducts(int minPrice, int maxPrice, String sortParam) {
        Sort sort = getSort(sortParam);
        List<SaleProduct> saleProducts = saleProductRepository.findBySalePriceBetween(minPrice, maxPrice, sort);
        return saleProducts.stream()
                .map(SaleProductListResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public SaleProductDetailResponse getSaleProduct(Long saleProductId) {
        SaleProduct saleProduct = saleProductRepository.findOne(saleProductId);
        return SaleProductDetailResponse.from(saleProduct);
    }

    private Sort getSort(String sortParam) {
        return switch (sortParam) {
            case "priceAsc" -> Sort.by(Sort.Direction.ASC, "salePrice");
            case "priceDesc" -> Sort.by(Sort.Direction.DESC, "salePrice");
            case "saleCountDesc" -> Sort.by(Sort.Direction.DESC, "saleCount");
            case "recentDesc" -> Sort.by(Sort.Direction.DESC, "createdAt"); // from BaseEntity
            default -> Sort.by(Sort.Direction.DESC, "createdAt");
        };
    }
}
