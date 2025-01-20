package com.frame2.server.core.product.infrastructure;

import com.frame2.server.core.product.domain.Category;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    default Category findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.CATEGORY_NOT_FOUND));
    }

    List<Category> findAllByParentCategoryIsNull();
}
