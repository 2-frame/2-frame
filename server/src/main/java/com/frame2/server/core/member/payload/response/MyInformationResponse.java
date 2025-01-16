package com.frame2.server.core.member.payload.response;

import com.frame2.server.core.member.domain.Member;

public record MyInformationResponse(
        Long id,
        String name,
        String nickname
) {
    public static MyInformationResponse from(Member member) {
        return new MyInformationResponse(member.getId(), member.getName(), member.getNickname());
    }
}
