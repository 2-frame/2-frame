package com.frame2.server.core.member.application;

import com.frame2.server.core.member.domain.EmailVerificationCode;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.member.infrastructure.EmailVerificationCodeRepository;
import com.frame2.server.core.member.infrastructure.MemberCredentialRepository;
import com.frame2.server.core.member.payload.request.EmailSecretValidateRequest;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberCredentialRepository memberCredentialRepository;
    private final EmailVerificationCodeRepository emailVerificationCodeRepository;

    @Override
    public void validate(EmailSecretValidateRequest request) {
        emailVerificationCodeRepository.findFirstEmailVerificationCode(request.memberId(), LocalDateTime.now())
                .filter(verificationCode -> verificationCode.match(request.secretCode()))
                .map(EmailVerificationCode::getMember)
                .map(Member::getId)
                .map(memberCredentialRepository::findOne)
                .orElseThrow(() -> new DomainException(ExceptionType.INVALID_SECRET_CODE))
                .active();
    }
}
