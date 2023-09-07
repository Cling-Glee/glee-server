package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import com.cling.glee.domain.entity.enums.ReactionType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="answer_reactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerReaction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="answer_id")
    private Answer answer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @Column(nullable = false)
    private Long userId; // 공감한 유저 아이디

}
