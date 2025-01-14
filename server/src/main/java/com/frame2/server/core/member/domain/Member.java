package com.frame2.server.core.member.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends BaseEntity {

    private String name;

    private String nickname;

    private String email;

    // 회원 생성에서 필요한 기능 ->
    // 필드 입력 받고 중복 확인하고
    // 유효성 검사
    // entity -> proxy + reflection, 필드는 final -> x, 생성 시점에 완성
    // JPA entity -> Save 완성, 객체 완성 !== Row 완성

    @Builder
    public Member(String name, String nickname, String email) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

}
