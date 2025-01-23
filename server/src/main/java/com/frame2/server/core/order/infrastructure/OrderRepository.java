package com.frame2.server.core.order.infrastructure;

import com.frame2.server.core.order.domain.Order;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order findOne(Long memberId, Long orderId) {
        return findByMemberIdAndOrderId(memberId, orderId)
                .orElseThrow(() -> new DomainException(ExceptionType.ORDER_NOT_FOUND));
    }

    @Query("SELECT o FROM Order o WHERE o.memberId = :memberId AND o.id = :orderId")
    Optional<Order> findByMemberIdAndOrderId(@Param("memberId") Long memberId, @Param("orderId") Long orderId);

    Page<Order> findAllByMemberId(Long memberId, Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.orderDetails WHERE o.memberId = :memberId AND o.id = :orderId")
    Order findWithOrderDetailsByMemberId(@Param("memberId") Long memberId, @Param("orderId") Long orderId);
}
