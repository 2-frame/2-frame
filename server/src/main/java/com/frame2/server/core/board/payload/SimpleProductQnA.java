package com.frame2.server.core.board.payload;

import com.frame2.server.core.board.domain.ProductQnA;
import com.frame2.server.core.example.domain.Example;
import com.frame2.server.core.example.payload.SimpleExample;
import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;


// 회원 - 나중에 조인 해야함
//private String user;

//        private String question;
//        private String manager;
//        private String answer;
//        private boolean answer_YN;
//        private boolean isDeleted;
//        private Date answer_date;
//private LocalDateTime createdAt;
//private LocalDateTime updatedAt;

public record SimpleProductQnA(Long id,
                               String user,
                               String question,
                               String answer,
                               boolean answer_YN,
                               Date answer_date,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {

    public static SimpleProductQnA from(ProductQnA productQnA) {
        return new SimpleProductQnA(
                productQnA.getId(),
                productQnA.getUser(),
                productQnA.getQuestion()),
                productQnA.

    }
}
