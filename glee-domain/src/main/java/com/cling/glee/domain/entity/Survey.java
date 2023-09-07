package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="surveys")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survey extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid; // 설문 UUID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String surveyContent; // 설문 내용

    @Column(nullable = false)
    private LocalDateTime endTime; // 종료 시간

//    @Column(nullable = false)
//    private Boolean status; // 상태

    @Column(nullable = false)
    private Boolean isHide; // 숨김 여부

    @Column(nullable = false)
    private Boolean isDeleted; // 삭제 여부

    @Column(nullable = false)
    private Boolean isMultiSelect; // 다중선택 가능여부

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyItem> surveyItems = new ArrayList<>();

}
