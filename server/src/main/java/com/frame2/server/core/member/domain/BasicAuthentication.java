package com.frame2.server.core.member.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "basic_authentications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicAuthentication extends BaseEntity {

    private String email;

    private String password;

    private int tryCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @Builder
    public BasicAuthentication(Member member, String email, String password) {
        this.member = member;
        this.email = email;
        this.password = password;
        this.tryCount = 0;
    }
}
