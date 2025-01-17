package com.frame2.server.core.board.domain;


import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQnA extends BaseEntity {

    // 회원 - 나중에 조인 해야함
    private String user_id;

    // 질문
    private String question;

    // 관리자
    private String manager;

    // 답변
    private String answer;

    // 답변 여부 : 기본값 false
    private boolean answer_YN;

    // 삭제 여부 : false
    private boolean isDeleted;

    // 답변 작성일 : null 가능
    private Date answer_date;

}
