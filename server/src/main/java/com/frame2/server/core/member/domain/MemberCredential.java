package com.frame2.server.core.member.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member_credentials")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCredential extends BaseEntity {

    private static final int MAX_TRY_COUNT = 5;

    private String email;

    @Column(nullable = false)
    private String password;

    private int tryCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    private AccountStatus accountStatus;

    @Builder
    public MemberCredential(Member member, String email, String password) {
        this.member = member;
        this.email = email;
        this.password = password;
        this.tryCount = 0;
        this.accountStatus = AccountStatus.INIT;
    }

    public LoginStatus tryLogin(String password) {
        switch (this.accountStatus) {
            case INIT -> throw new DomainException(ExceptionType.REQUIRE_EMAIL_AUTHENTICATION);
            case DORMANT -> throw new DomainException(ExceptionType.REQUIRE_UNLOCK_DORMANT);
            case STOP -> throw new DomainException(ExceptionType.ACCOUNT_LOCKED);
        }

        if (password.equals(this.password)) {
            return LoginStatus.OK;
        }

        increaseTryCount();
        return LoginStatus.FAIL;
    }

    private void increaseTryCount() {
        this.tryCount += 1;

        if (this.tryCount == MAX_TRY_COUNT) {
            this.accountStatus = AccountStatus.STOP;
        }
    }

    public Long getMemberId() {
        return member.getId();
    }

    public void active() {
        this.accountStatus = AccountStatus.ACTIVE;
    }
}
