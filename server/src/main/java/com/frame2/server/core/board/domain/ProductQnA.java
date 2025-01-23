package com.frame2.server.core.board.domain;


import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.product.domain.Product;
import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQnA extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // 질문
    private String question;

    // 추가 : 질문 제목
    private String title;

    // 관리자
    private String manager;

    // 답변
    private String answer;

    // 답변 상태 : 기본값 N
    @Enumerated(EnumType.STRING)
    @Column(name = "answer_YN")
    private AnswerStatus answerYN;

    // 답변 작성일 : null 가능
    private LocalDateTime answerDate;

    // question 수정 메서드
    public ProductQnA updateQuestion(String newTitle, String newQuestion) {
        this.title = newTitle;
        this.question = newQuestion;
        return this;
    }

    // answer 등록 메서드
    // answer 엔티티가 따로 존재하지 않기 때문에 productQnA를 수정 하는 것에 속함
    public ProductQnA createAnswer(String answer, String manager) {
        this.answer = answer;
        this.manager = manager;
        this.answerYN = AnswerStatus.Y;
        return this;
    }

    // answer 수정 메서드
    public ProductQnA updateAnswer(String newAnswer) {
        this.answer = newAnswer;
        return this;
    }

}
