package com.frame2.server.core.member.infrastructure;

import com.frame2.server.core.member.domain.EmailVerificationCode;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailVerificationCodeRepository extends JpaRepository<EmailVerificationCode, Long> {


    @Query("""
            select evc from EmailVerificationCode evc
                where evc.member.id = :memberId
                and evc.expireTime >= :baseDateTime
                order by evc.expireTime desc
                limit 1
            """)
    Optional<EmailVerificationCode> findFirstEmailVerificationCode(
            @Param("memberId") Long memberId,
            @Param("baseDateTime") LocalDateTime baseDateTime
    );


}
