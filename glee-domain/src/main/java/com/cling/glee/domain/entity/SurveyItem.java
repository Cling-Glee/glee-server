package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="survey_items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="survey_id")
    private Survey survey;

    @Column(nullable = false)
    private String surveyItemContent;

    @OneToMany(mappedBy = "surveyItem", cascade = CascadeType.ALL)
    private List<SurveyVote> surveyVotes = new ArrayList<>();
}
