package com.frame2.server.core.order.application;

import com.frame2.server.core.order.domain.Order;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.order.infrastructure.OrderRepository;
import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.product.infrastructure.SaleProductRepository;
import com.frame2.server.core.support.response.IdResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SaleProductRepository saleProductRepository;

    @Transactional
    public IdResponse createOrder(OrderCreateRequest request) {
        
        // 주문 생성
        Order order = request.toEntity();
        orderRepository.save(order);

        // 판매상품 id 리스트
        List<Long> saleProductIdList = request.orderDetails().stream()
                .map(orderDetail -> orderDetail.saleProductId())
                .distinct()
                .toList();

        // 판매상품 id 리스트로 판매상품을 리스트로 받아옴
        List<SaleProduct> saleProductList = saleProductRepository.findAllById(saleProductIdList);

        // TODO: Exception 정의 필요
        // 주문 요청의 판매상품 개수와 레포지토리에서 가져온 판매상품 개수 비교
        if(saleProductIdList.size() != saleProductList.size()) {
            throw new IllegalArgumentException("판매상품 중 일부가 존재하지 않습니다.");
        }

        // <ID, SaleProduct>를 미리 매핑
        Map<Long, SaleProduct> saleProductMap = saleProductList.stream()
                .collect(Collectors.toMap(SaleProduct::getId, sp -> sp));

        List<OrderDetail> orderDetails = request.orderDetails().stream()
                .map(orderDetail -> {
                    // 주문 상세와 판매상품을 매핑
                    SaleProduct saleProduct = saleProductMap.get(orderDetail.saleProductId());

                    // 주문 상세 생성
                    return OrderDetail.builder()
                            .saleProduct(saleProduct)
                            .quantity(orderDetail.quantity())
                            .price(saleProduct.getProduct().getPrice())
                            .build();

                }).toList();

        order.addOrderDetails(orderDetails);

        return new IdResponse(order.getId());
    }
}
