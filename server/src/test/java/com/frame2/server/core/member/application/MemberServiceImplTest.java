package com.frame2.server.core.member.application;

import static com.frame2.server.core.support.exception.ExceptionType.DUPLICATE_MEMBER_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.member.payload.request.SignupRequest;
import com.frame2.server.core.support.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 성공한다")
    void 회원가입_성공() {
        memberService.signup(new SignupRequest("의연", "euiyeon@gmail.com", "의연", "1234"));

        // 회원가입 성공하면 회원 하나 증가
        assertThat(memberRepository.count())
                .isEqualTo(1);
    }

    @Test
    void 동일한_이메일을_사용한_계정이_회원가입하면_오류가_발생한다() {
        assertThatThrownBy(() -> {
            memberService.signup(new SignupRequest("의연", "euiyeon@gmail.com", "의연", "1234"));
            memberService.signup(new SignupRequest("의연", "euiyeon@gmail.com", "의연", "1234"));
        }).isInstanceOf(DomainException.class)
                .hasMessageContaining(DUPLICATE_MEMBER_ERROR.getMessage());
    }
}