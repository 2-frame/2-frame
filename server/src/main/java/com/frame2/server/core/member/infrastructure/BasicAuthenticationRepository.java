package com.frame2.server.core.member.infrastructure;

import com.frame2.server.core.member.domain.BasicAuthentication;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicAuthenticationRepository extends JpaRepository<BasicAuthentication, Long> {

    Optional<BasicAuthentication> findByEmail(String email);
}
