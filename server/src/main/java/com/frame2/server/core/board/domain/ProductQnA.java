package com.frame2.server.core.board.domain;


import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQnA extends BaseEntity {

    // 회원 - 나중에 조인 해야함
    private String user;

    // 질문
    private String question;

    // 관리자
    private String manager;

    // 질문
    private String answer;

    // 답변 여부 : 기본값 false
    private boolean answer_YN;

    // 삭제 여부 : false
    private boolean isDeleted;

    // 답변 작성일 : null 가능
    private Date answer_date;


//    @Column
//    @CreationTimestamp
//    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }
    public LocalDateTime getUpdatedAt() {
        return super.updatedAt;
    }

//    @Column
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;


    @Builder
    public ProductQnA(String user, String question, String manager, String answer, boolean answer_YN, boolean isDeleted, Date answer_date) {
        this.user = user;
        this.question = question;
        this.manager = manager;
        this.answer = answer;
        this.answer_YN = answer_YN;
        this.isDeleted = isDeleted;
        this.answer_date = answer_date;
    }

    public
}
