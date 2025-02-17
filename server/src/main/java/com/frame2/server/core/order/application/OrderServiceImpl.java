package com.frame2.server.core.order.application;

import com.frame2.server.core.order.domain.Order;
import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.order.infrastructure.OrderDetailRepository;
import com.frame2.server.core.order.infrastructure.OrderRepository;
import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.product.domain.SaleProduct;
import com.frame2.server.core.product.domain.Stock;
import com.frame2.server.core.product.infrastructure.SaleProductRepository;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import com.frame2.server.core.support.response.IdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public IdResponse createOrder(Long memberId, OrderCreateRequest request) {
        // 주문 생성
        Order order = request.toEntity(memberId);
        orderRepository.save(order);

        // 판매상품 id 리스트
        List<Long> saleProductIdList = request.orderDetails().stream()
                .map(orderDetail -> orderDetail.saleProductId())
                .distinct()
                .toList();

        // 판매상품 id 리스트로 판매상품을 리스트로 받아옴
        List<SaleProduct> saleProductList = saleProductRepository.findAllById(saleProductIdList);

        // 주문 요청의 판매상품 개수와 레포지토리에서 가져온 판매상품 개수 비교
        if(saleProductIdList.size() != saleProductList.size()) {
            throw new DomainException(ExceptionType.SALE_PRODUCT_MISMATCH);
        }

        // <ID, SaleProduct>를 미리 매핑
        Map<Long, SaleProduct> saleProductMap = saleProductList.stream()
                .collect(Collectors.toMap(SaleProduct::getId, sp -> sp));

        List<OrderDetail> orderDetails = request.orderDetails().stream()
                .map(orderDetail -> {
                    // 주문 상세와 판매상품을 매핑
                    SaleProduct saleProduct = saleProductMap.get(orderDetail.saleProductId());

                    // 판매상품의 재고 검증 및 차감
                    Stock stock = saleProduct.getStock();
                    stock.reduceQuantity(orderDetail.quantity());

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
    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long memberId, Long orderId) {
        Order order = orderRepository.findOne(memberId, orderId);
        return OrderResponse.from(order);
    }

    // 주문 내역 전체조회 - 멤버id로 전체 조회
    @Override
    @Transactional(readOnly = true)
    public PagedModel<OrderResponse> getOrders(Long memberId, Pageable pageable) {
        return new PagedModel<>(orderRepository.findAllByMemberId(memberId, pageable)
                .map(OrderResponse::from));
    }

    // 주문 상세 내역 단건 조회 - 주문상세id로 단건 조회
    @Override
    @Transactional(readOnly = true)
    public OrderDetailResponse getOderDetail(Long memberId, Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findOne(memberId, orderDetailId);
        return OrderDetailResponse.from(orderDetail);
    }

    // 주문 상세 내역 전체조회 - 주문id로 주문 상세 내역 전체조회
    @Override
    @Transactional(readOnly = true)
    public List<OrderDetailResponse> getOrderDetails(Long memberId, Long orderId) {
        return orderDetailRepository.findAllByMemberIdAndOrderId(memberId, orderId).stream()
                .map(OrderDetailResponse::from)
                .toList();
    }

    // 주문 전체 취소 - 주문id로 전체 취소
        // 주문이 취소되면 모든 주문 상세도 취소
        // 주문 상세에 포함된 상품 재고를 주문 수량만큼 가산
    @Override
    public IdResponse cancelOrder(Long memberId, Long orderId) {
        Order order = orderRepository.findWithOrderDetailsByMemberId(memberId, orderId);
        order.cancelOrder();
        return new IdResponse(order.getId());
    }

    // 주문 부분 취소 - 주문 상세id로 취소
        // 주문 상세 취소 -> 주문 상태 변경(ORDER_PARTICAL_CANCELED)
        // 주문 상세에 포함된 상품 재고를 주문 수량만큼 가산
    @Override
    public IdResponse cancelOrderDetail(Long memberId, Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findWithOrderAndSaleProductByMemberId(memberId, orderDetailId);
        orderDetail.cancelOrderDetail();
        return new IdResponse(orderDetail.getId());
    }

}