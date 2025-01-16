package com.frame2.server.core.cart.infrastructure;

import com.frame2.server.core.cart.domain.CartItem;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    default CartItem findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.PRODUCT_NOT_FOUND));
    }

    Optional<CartItem> findByMemberId(Long memberId);
}
