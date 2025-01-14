package com.frame2.server.core.member.payload.request;

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
}
