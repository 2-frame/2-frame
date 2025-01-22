package com.frame2.crawler.infrastructure;

import com.frame2.crawler.domain.Category;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    default Category findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("엔티티가 없어요"));
    }

    List<Category> findAllByParentCategoryIsNull();
}
