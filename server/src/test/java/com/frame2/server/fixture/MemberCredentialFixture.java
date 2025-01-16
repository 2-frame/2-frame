package com.frame2.server.fixture;

import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.member.domain.MemberCredential;

public class MemberCredentialFixture {

    public static MemberCredential create(Member member, String password) {
        var credential = MemberCredential.builder()
                .email(member.getEmail())
                .password(password)
                .member(member)
                .build();

        credential.active();
        
        return credential;
    }
}
