package com.frame2.server.core.order.infrastructure;

import com.frame2.server.core.order.domain.OrderDetail;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    default OrderDetail findOne(Long memberId, Long orderDetailId) {
        return findByMemberIdAndOrderDetailId(memberId, orderDetailId)
                .orElseThrow(() -> new DomainException(ExceptionType.ORDER_DETAIL_NOT_FOUND));
    }

    @Query("SELECT od " +
            "FROM OrderDetail od " +
            "JOIN FETCH od.order o " +
            "JOIN FETCH od.saleProduct sp " +
            "WHERE o.memberId = :memberId AND od.id = :orderDetailId")
    Optional<OrderDetail> findByMemberIdAndOrderDetailId(@Param("memberId") Long memberId,
                                                         @Param("orderDetailId") Long orderDetailId);

    @Query("SELECT od " +
            "FROM OrderDetail od " +
            "JOIN od.order o " +
            "WHERE o.memberId = :memberId AND o.id = :orderId")
    List<OrderDetail> findAllByMemberIdAndOrderId(@Param("memberId") Long memberId,
                                                  @Param("orderId") Long orderId);

    @Query("SELECT od " +
            "FROM OrderDetail od " +
            "JOIN FETCH od.order o " +
            "JOIN FETCH od.saleProduct sp " +
            "JOIN FETCH sp.stock s " +
            "WHERE o.memberId = :memberId AND od.id = :orderDetailId")
    OrderDetail findWithOrderAndSaleProductByMemberId(@Param("memberId") Long memberId,
                                                      @Param("orderDetailId") Long orderDetailId);
}
