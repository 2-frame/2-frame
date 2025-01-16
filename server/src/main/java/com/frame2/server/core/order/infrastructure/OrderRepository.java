package com.frame2.server.core.order.infrastructure;

import com.frame2.server.core.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
