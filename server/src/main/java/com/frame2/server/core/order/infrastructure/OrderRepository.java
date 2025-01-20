package com.frame2.server.core.order.infrastructure;

import com.frame2.server.core.order.domain.Order;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order findOne(Long id){
        return findById(id).orElseThrow(() -> new DomainException(ExceptionType.ORDER_NOT_FOUND));
    }

    List<Order> findAllByMemberId(Long memberId);
}
