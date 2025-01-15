package com.frame2.server.fixture;

import com.frame2.server.core.member.domain.BasicAuthentication;
import com.frame2.server.core.member.domain.Member;

public class BasicAuthenticationFixture {

    public static BasicAuthentication create(Member member, String password) {
        return BasicAuthentication.builder()
                .email(member.getEmail())
                .password(password)
                .member(member)
                .build();
    }
}
