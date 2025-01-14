package com.frame2.server.core.member.payload.request;

public record SignupRequest(
        String name,
        String email,
        String nickname,
        String password
) {
}
