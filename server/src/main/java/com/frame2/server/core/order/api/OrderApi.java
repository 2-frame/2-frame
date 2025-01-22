package com.frame2.server.core.order.api;

import com.frame2.server.core.order.application.OrderService;
import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.support.annotations.MemberOnly;
import com.frame2.server.core.support.response.IdResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApi implements OrderApiSpec{

    private final OrderService orderServiceImpl;
    
    // 주문 생성
    @Override
    @MemberOnly
    @PostMapping
    public IdResponse createOrder(@RequestBody @Valid OrderCreateRequest request) {
        return orderServiceImpl.createOrder(request);
    }
    
    // 주문 단건 조회
    @Override
    @MemberOnly
    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        return orderServiceImpl.getOrder(orderId);
    }
    
    // 주문 전체 조회
    @Override
    @MemberOnly
    @GetMapping("/members/{memberId}")
    public PagedModel<OrderResponse> getOrders(@PathVariable Long memberId,
                                               @RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "size", defaultValue = "15") int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return orderServiceImpl.getOrders(memberId, pageable);
    }
    
    // 주문 상세 단건 조회
    @Override
    @MemberOnly
    @GetMapping("/details/{orderDetailId}")
    public OrderDetailResponse getOderDetail(@PathVariable Long orderDetailId){
        return orderServiceImpl.getOderDetail(orderDetailId);
    }
    
    // 주문 상세 전체 조회
    @Override
    @MemberOnly
    @GetMapping("/{orderId}/details")
    public List<OrderDetailResponse> getOrderDetails(@PathVariable Long orderId) {
        return orderServiceImpl.getOrderDetails(orderId);
    }

    // 주문 전체 취소
    @Override
    @MemberOnly
    @PatchMapping("/{orderId}")
    public IdResponse cancelOrder(@PathVariable Long orderId) {
        return orderServiceImpl.cancelOrder(orderId);
    }

    // 주문 부분 취소
    @Override
    @MemberOnly
    @PatchMapping("/details/{orderDetailId}")
    public IdResponse cancelOrderDetail(@PathVariable Long orderDetailId) {
        return orderServiceImpl.cancelOrderDetail(orderDetailId);
    }
}
