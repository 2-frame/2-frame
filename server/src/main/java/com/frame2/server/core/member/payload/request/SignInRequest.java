package com.frame2.server.core.member.payload.request;

public record SignInRequest(
        String email,
        String password
) {
}
