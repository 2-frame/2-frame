package com.frame2.server.core.member.infrastructure;

import com.frame2.server.core.member.domain.MemberCredential;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCredentialRepository extends JpaRepository<MemberCredential, Long> {

    Optional<MemberCredential> findByEmail(String email);
}

