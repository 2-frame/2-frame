package com.frame2.server.core.member.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.frame2.server.core.member.domain.EmailVerificationCode;
import com.frame2.server.fixture.MemberFixture;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailVerificationCodeRepositoryTest {

    @Autowired
    private EmailVerificationCodeRepository emailVerificationCodeRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Test
    void 이메일_인증_쿼리는_만료시간이_가장_늦은_쿼리가_먼저_조회됩니다() {

        var member = MemberFixture.create();
        var now = LocalDateTime.now();

        var verificationCodes = IntStream.iterate(1, i -> i + 1)
                .limit(10)
                .mapToObj(i -> new EmailVerificationCode("code" + i, now.plusMinutes(i), member))
                .toList();

        memberRepository.save(member);
        emailVerificationCodeRepository.saveAll(verificationCodes);

        var verificationCode = emailVerificationCodeRepository.findFirstEmailVerificationCode(member.getId(), now)
                .orElseThrow();

        assertThat(verificationCode.getCode()).isEqualTo("code10");
    }
}