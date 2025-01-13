package com.frame2.server.core.example.infrastructure;

import com.frame2.server.core.example.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {

}
