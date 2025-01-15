package com.frame2.server.fixture;

import com.frame2.server.core.member.domain.Member;

public class MemberFixture {

    public static Member create(String email) {
        return Member.builder()
                .email(email)
                .nickname("테스터")
                .name("2frame")
                .build();
    }
}
