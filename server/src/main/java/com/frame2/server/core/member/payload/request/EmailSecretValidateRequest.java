package com.frame2.server.core.member.payload.request;

public record EmailSecretValidateRequest(
        Long memberId,
        String secretCode
) {
}
