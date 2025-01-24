package com.frame2.server.core.board.domain;

import com.frame2.server.core.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "faq")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQ extends BaseEntity {

    // 카테고리
    @Enumerated(EnumType.STRING)
    private FAQCategory category;

    // 순서
    @Column(name = "`order`")
    private int order;

    // 질문
    private String question;

    // 답변
    private String answer;

}
