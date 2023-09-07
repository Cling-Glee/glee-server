package com.cling.glee.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="survey_votes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="survey_item_id")
    private SurveyItem surveyItem;

    @Column
    private Long userId;

    @Column
    private Long surveyId;

}
