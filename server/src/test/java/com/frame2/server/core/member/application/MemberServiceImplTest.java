package com.frame2.server.core.member.application;

import static com.frame2.server.core.support.exception.ExceptionType.DUPLICATE_EMAIL;
import static com.frame2.server.core.support.exception.ExceptionType.DUPLICATE_NICKNAME;
import static com.frame2.server.core.support.exception.ExceptionType.LOGIN_FAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.frame2.server.core.member.infrastructure.BasicAuthenticationRepository;
import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.member.payload.request.SignInRequest;
import com.frame2.server.core.member.payload.request.SignupRequest;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.fixture.BasicAuthenticationFixture;
import com.frame2.server.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberServiceImplTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BasicAuthenticationRepository basicAuthenticationRepository;

    @Test
    @DisplayName("회원가입 성공한다")
    void 회원가입_성공() {
        var signupInfo = memberService.signup(new SignupRequest("의연", "euiyeon@gmail.com", "의연", "1234"));

        // 회원가입 성공하면 회원 하나 증가
        var member = memberRepository.findOne(signupInfo.memberId());

        assertAll(
                () -> assertThat(member.getName()).isEqualTo("의연"),
                () -> assertThat(member.getEmail()).isEqualTo("euiyeon@gmail.com")
        );

        assertThat(memberRepository.count())
                .isEqualTo(1);
    }

    @Test
    void 동일한_이메일을_사용한_계정이_회원가입하면_오류가_발생한다() {
        assertThatThrownBy(() -> {
            memberService.signup(new SignupRequest("의연", "euiyeon@gmail.com", "의연1", "1234"));
            memberService.signup(new SignupRequest("의연", "euiyeon@gmail.com", "의연2", "1234"));
        }).isInstanceOf(DomainException.class)
                .hasMessageContaining(DUPLICATE_EMAIL.getMessage());
    }

    @Test
    void 동일한_닉네임을_사용한_계정이_회원가입을_할_수_없습니다() {
        assertThatThrownBy(() -> {
            memberService.signup(new SignupRequest("의연", "euiyeon1@gmail.com", "의연1", "1234"));
            memberService.signup(new SignupRequest("의연", "euiyeon2@gmail.com", "의연1", "1234"));
        }).isInstanceOf(DomainException.class)
                .hasMessageContaining(DUPLICATE_NICKNAME.getMessage());
    }


    @Nested
    class 로그인_테스트 {

        @Test
        void 로그인_성공하는_경우_회원_아이디를_가집니다() {
            var member = memberRepository.save(MemberFixture.create("sample@gmail.com"));
            basicAuthenticationRepository.save(BasicAuthenticationFixture.create(member, "password"));

            var signInInfo = memberService.signIn(new SignInRequest("sample@gmail.com", "password"));

            assertThat(member.getId()).isEqualTo(signInInfo.memberId());
        }

        @Test
        void 이메일이_존재하지_않거나_비밀번호가_다른_경우_로그인에_실패합니다() {
            var member = memberRepository.save(MemberFixture.create("sample@gmail.com"));
            basicAuthenticationRepository.save(BasicAuthenticationFixture.create(member, "password"));

            assertAll(
                    () -> assertThatThrownBy(
                            () -> memberService.signIn(new SignInRequest("uunknown@gmail.com", "password")))
                            .isInstanceOf(DomainException.class)
                            .hasMessageContaining(LOGIN_FAIL.getMessage()),
                    () -> assertThatThrownBy(
                            () -> memberService.signIn(new SignInRequest("sample@gmail.com", "otherpassword")))
                            .isInstanceOf(DomainException.class)
                            .hasMessageContaining(LOGIN_FAIL.getMessage())
            );
        }
    }

}