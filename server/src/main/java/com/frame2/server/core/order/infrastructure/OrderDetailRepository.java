package com.frame2.server.core.order.infrastructure;

import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    default OrderDetail findOne(Long id) {
        return findById(id).orElseThrow(() -> new DomainException(ExceptionType.ORDER_DETAIL_NOT_FOUND));
    }

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
    List<OrderDetail> findAllByOrderId(@Param("orderId") Long orderId);
}
