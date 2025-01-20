package com.frame2.server.core.order.application;

import com.frame2.server.core.order.domain.Order;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.order.infrastructure.OrderDetailRepository;
import com.frame2.server.core.order.infrastructure.OrderRepository;
import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
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
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final SaleProductRepository saleProductRepository;

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
                            .order(order)
                            .saleProduct(saleProduct)
                            .quantity(orderDetail.quantity())
                            .price(saleProduct.getProduct().getPrice())
                            .build();
                }).toList();

        order.addOrderDetails(orderDetails);
        order.updateTotalPrice();
        order.updateProductName();

        return new IdResponse(order.getId());
    }
    
    // 주문 내역 단건 조회 - 주문id로 단건 조회
    @Override
    public OrderResponse getOrder(Long orderId) {

        Order order = orderRepository.findOne(orderId);
        return OrderResponse.from(order);
    }

    // TODO : 페이징 처리
    // 주문 내역 전체조회 - 멤버id로 전체 조회
    @Override
    public List<OrderResponse> getOrders(Long memberId) {

        return orderRepository.findAllByMemberId(memberId).stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }

    // 주문 상세 내역 단건 조회 - 주문상세id로 단건 조회
    @Override
    public OrderDetailResponse getOderDetail(Long orderDetailId) {

        OrderDetail orderDetail = orderDetailRepository.findOne(orderDetailId);
        return OrderDetailResponse.from(orderDetail);
    }

    // TODO : 페이징 처리
    // 주문 상세 내역 전체조회 - 주문id로 주문 상세 내역 전체조회
    @Override
    public List<OrderDetailResponse> getOrderDetails(Long orderId) {

        return orderDetailRepository.findAllByOrderId(orderId).stream()
                .map(OrderDetailResponse::from)
                .collect(Collectors.toList());
    }
}