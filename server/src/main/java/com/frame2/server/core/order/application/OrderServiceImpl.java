package com.frame2.server.core.order.application;

import com.frame2.server.core.order.domain.Order;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.order.infrastructure.OrderDetailRepository;
import com.frame2.server.core.order.infrastructure.OrderRepository;
import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.product.infrastructure.ProductRepository;
import com.frame2.server.core.product.infrastructure.SaleProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final SaleProductRepository saleProductRepository;

    @Transactional
    public Long createOrder(OrderCreateRequest request) {
        
        // 주문 생성
        Order order = request.toEntity();
        orderRepository.save(order);
        
        // 주문 상세 생성
        List<OrderDetail> orderDetails = request.orderDetails().stream()
                .map(orderDetail -> {
                    // 판매상품 조회
                    SaleProduct saleProduct = saleProductRepository.findById(orderDetail.saleProductId())
                            .orElseThrow(()-> new IllegalArgumentException("판매상품 ID가 유효하지 않습니다."));
                    
                    // 주문 상세 생성
                    return OrderDetail.builder()
                            .order(order)
                            .saleProduct(saleProduct)
                            .quantity(orderDetail.quantity())
                            .price(saleProduct.getProduct().getPrice())
                            .build();
                })
                .peek(orderDetail -> order.getOrderDetails().add(orderDetail))
                .toList();

        // 주문 번호 반환
        return order.getId();
    }
}
