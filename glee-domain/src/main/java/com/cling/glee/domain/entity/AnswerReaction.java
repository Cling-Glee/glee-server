package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.enums.ReactionTypeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="answer_reactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="answer_id")
    private Answer answer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionTypeEnum reactionType;

}
