package com.frame2.server.core.member.application;

import com.frame2.server.core.mail.application.EmailClient;
import com.frame2.server.core.member.domain.EmailVerificationCode;
import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.member.domain.RandomGenerator;
import com.frame2.server.core.member.infrastructure.EmailVerificationCodeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberEmailService {

    private static final String SEND_SECRET_TITLE_FORMAT = "%s님의 인증 코드 발신 내역입니다.";

    private final EmailClient emailClient;
    private final RandomGenerator generator;
    private final EmailVerificationCodeRepository emailVerificationCodeRepository;

    public void sendSecret(Member member) {
        var secretCode = generator.generate();
        var emailVerificationCode = EmailVerificationCode.builder()
                .code(secretCode)
                .member(member)
                .expireTime(LocalDateTime.now().plusMinutes(5))
                .build();

        emailVerificationCodeRepository.save(emailVerificationCode);

        emailClient.send(
                member.getEmail(),
                String.format(SEND_SECRET_TITLE_FORMAT, member.getName()),
                secretCode
        );
    }
}
