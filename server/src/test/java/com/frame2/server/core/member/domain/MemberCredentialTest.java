package com.frame2.server.core.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import com.frame2.server.fixture.MemberCredentialFixture;
import com.frame2.server.fixture.MemberFixture;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberCredentialTest {

    @Test
    void 계정의_비밀번호를_틀리면_시도횟수가_1_증가합니다() {
        var memberCredential = MemberCredentialFixture.create(MemberFixture.create("email@email.com"), "password");
        var loginStatus = memberCredential.tryLogin("other-password");

        assertAll(
                () -> assertThat(loginStatus).isEqualTo(LoginStatus.FAIL),
                () -> assertThat(memberCredential.getTryCount()).isEqualTo(1)
        );
    }

    @Test
    void 비밀번호를_5번_시도해서_실패하면_계정이_잠긴다() {
        var memberCredential = MemberCredentialFixture.create(MemberFixture.create("email@email.com"), "password");

        memberCredential.active();
        IntStream.rangeClosed(1, 5).forEach((_) -> memberCredential.tryLogin("other-password"));

        assertAll(
                () -> assertThat(memberCredential.getAccountStatus()).isEqualTo(AccountStatus.STOP),
                () -> assertThat(memberCredential.getTryCount()).isEqualTo(5)
        );

    }

    @Test
    void 로그인_시도할_경우_계정이_잠금_상태면_에러가_발생한다() {
        var memberCredential = MemberCredentialFixture.create(MemberFixture.create("email@email.com"), "password");

        memberCredential.active();

        IntStream.rangeClosed(1, 5).forEach((_) -> memberCredential.tryLogin("other-password"));

        assertThatThrownBy(() -> memberCredential.tryLogin("other-password"))
                .isInstanceOf(DomainException.class)
                .hasMessageContaining(ExceptionType.ACCOUNT_LOCKED.getMessage());
    }

    @Test
    void 로그인에_성공합니다() {
        var memberCredential = MemberCredentialFixture.create(MemberFixture.create("email@email.com"), "password");

        memberCredential.active();
        var loginStatus = memberCredential.tryLogin("password");

        assertThat(loginStatus).isEqualTo(LoginStatus.OK);
    }

}