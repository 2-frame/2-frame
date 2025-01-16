package com.frame2.server.core.member.payload.request;

import com.frame2.server.core.member.domain.BasicAuthentication;
import com.frame2.server.core.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "이름은 필수값입니다.")
        @Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 이하여야합니다.")
        String name,

        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "닉네임은 필수값입니다.")
        @Size(min = 2, max = 20, message = "닉네임 2글자 이상 20글자 이하여야합니다.")
        String nickname,

        @NotBlank(message = "비빌번호는 필수값입니다.")
        @Size(min = 8, max = 15, message = "비밀번호는 8글자 이상 15글자 이하여야합니다.")
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
