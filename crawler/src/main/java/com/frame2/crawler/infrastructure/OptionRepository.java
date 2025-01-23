package com.frame2.crawler.infrastructure;

import com.frame2.crawler.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
