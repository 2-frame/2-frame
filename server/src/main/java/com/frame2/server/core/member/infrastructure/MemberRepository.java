package com.frame2.server.core.member.infrastructure;

import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findOne(Long id) {
        return findById(id)
                .orElseThrow(() -> new DomainException(ExceptionType.MEMBER_NOT_FOUND));
    }

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
