package com.frame2.server.core.member.infrastructure;

import com.frame2.server.core.member.domain.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationCodeRepository extends JpaRepository<EmailVerificationCode, Long> {

}
