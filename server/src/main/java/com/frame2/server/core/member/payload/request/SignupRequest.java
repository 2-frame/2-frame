package com.frame2.server.core.member.payload.request;

import com.frame2.server.core.member.domain.BasicAuthentication;
import com.frame2.server.core.member.domain.Member;

public record SignupRequest(
        String name,
        String email,
        String nickname,
        String password
) {

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .build();
    }

    public BasicAuthentication toEntity(Member member) {
        return BasicAuthentication.builder()
                .member(member)
                .email(email)
                .password(password)
                .build();
    }
}
