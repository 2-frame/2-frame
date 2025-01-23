package com.frame2.server.core.order.api;

import com.frame2.server.core.order.application.OrderService;
import com.frame2.server.core.order.payload.request.OrderCreateRequest;
import com.frame2.server.core.order.payload.response.OrderDetailResponse;
import com.frame2.server.core.order.payload.response.OrderResponse;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.request.User;
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

    private final OrderService orderService;
    
    // 주문 생성
    @Override
    @MemberOnly
    @PostMapping
    public IdResponse createOrder(@Auth User user, @RequestBody @Valid OrderCreateRequest request) {
        return orderService.createOrder(user.id() ,request);
    }
    
    // 주문 단건 조회
    @Override
    @MemberOnly
    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@Auth User user, @PathVariable Long orderId) {
        return orderService.getOrder(user.id(), orderId);
    }
    
    // 주문 전체 조회
    @Override
    @MemberOnly
    @GetMapping("/members")
    public PagedModel<OrderResponse> getOrders(@Auth User user,
                                               @RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "size", defaultValue = "15") int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return orderService.getOrders(user.id(), pageable);
    }
    
    // 주문 상세 단건 조회
    @Override
    @MemberOnly
    @GetMapping("/details/{orderDetailId}")
    public OrderDetailResponse getOderDetail(@Auth User user, @PathVariable Long orderDetailId){
        return orderService.getOderDetail(user.id(), orderDetailId);
    }
    
    // 주문 상세 전체 조회
    @Override
    @MemberOnly
    @GetMapping("/{orderId}/details")
    public List<OrderDetailResponse> getOrderDetails(@Auth User user, @PathVariable Long orderId) {
        return orderService.getOrderDetails(user.id(), orderId);
    }

    // 주문 전체 취소
    @Override
    @MemberOnly
    @PatchMapping("/{orderId}")
    public IdResponse cancelOrder(@Auth User user, @PathVariable Long orderId) {
        return orderService.cancelOrder(user.id(), orderId);
    }

    // 주문 부분 취소
    @Override
    @MemberOnly
    @PatchMapping("/details/{orderDetailId}")
    public IdResponse cancelOrderDetail(@Auth User user, @PathVariable Long orderDetailId) {
        return orderService.cancelOrderDetail(user.id(), orderDetailId);
    }
}
