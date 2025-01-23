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

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order findOne(Long id){
        return findById(id).orElseThrow(() -> new DomainException(ExceptionType.ORDER_NOT_FOUND));
    }

    Page<Order> findAllByMemberId(Long memberId, Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.orderDetails WHERE o.id = :orderId")
    Order findWithOrderDetails(@Param("orderId") Long orderId);
}
