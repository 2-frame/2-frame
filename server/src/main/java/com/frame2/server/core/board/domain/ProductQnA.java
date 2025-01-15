package com.frame2.server.core.board.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ProductQnA {
    
    @Id
    private Long id;

    // 회원
    private String user;

    // 질문
    private String question;

    // 관리자
    private String manager;

    // 질문
    private String answer;

    // 답변 여부 : 기본값 false
    private boolean answer_YN;

    // 질문 작성일
    private Date creation_date;

    // 변경일
    private Date updated_date;

    // 삭제 여부
    private boolean isDeleted;
    // 답변 작성일 : null 가능
    private Date answer_date;
}
