package com.frame2.server.core.member.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member_credentials")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCredential extends BaseEntity {

    private String email;

    @Column(nullable = false)
    private String password;

    private int tryCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @Builder
    public MemberCredential(Member member, String email, String password) {
        this.member = member;
        this.email = email;
        this.password = password;
        this.tryCount = 0;
    }

    public LoginStatus tryLogin(String password) {
        if (password.equals(this.password)) {
            return LoginStatus.OK;
        }

        tryCount += 1;
        return LoginStatus.FAIL;
    }

    public boolean comparePassword(String password) {
        return Objects.equals(this.password, password);
    }

    public Long getMemberId() {
        return member.getId();
    }

    public boolean isAccountLocked() {
        return member.getAccountStatus() == AccountStatus.STOP;
    }
}
