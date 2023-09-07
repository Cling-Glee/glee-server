package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="answers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id")
    private Question question;

    @Column(nullable = false, length = 1000)
    private String answerContent;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<AnswerReaction> answerReactions = new ArrayList<>();

}
