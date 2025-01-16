package com.frame2.server.core.member.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberCredentialTest {

    @Test
    void 비밀번호를_비교하여_결과를_반환합니다() {
        var basicAuthentication = new MemberCredential(new Member(), "email@email.com", "real-password");

        var compareResult = basicAuthentication.comparePassword("other-password");

        assertThat(compareResult).isFalse();
    }

}