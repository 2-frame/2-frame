package com.frame2.server.core.member.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "email_verification_codes")
public class EmailVerificationCode extends BaseEntity {


    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expireTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @Builder
    public EmailVerificationCode(String code, LocalDateTime expireTime, Member member) {
        this.code = code;
        this.expireTime = expireTime;
        this.member = member;
    }

    public boolean match(String secretCode) {
        return this.code.equals(secretCode);
    }
}
