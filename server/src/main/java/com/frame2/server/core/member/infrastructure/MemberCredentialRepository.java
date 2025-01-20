package com.frame2.server.core.member.infrastructure;

import com.frame2.server.core.member.domain.MemberCredential;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCredentialRepository extends JpaRepository<MemberCredential, Long> {

    Optional<MemberCredential> findByEmail(String email);

    default MemberCredential findOne(Long memberId) {
        return findById(memberId)
                .orElseThrow(() -> new DomainException(ExceptionType.MEMBER_NOT_FOUND));
    }
}

