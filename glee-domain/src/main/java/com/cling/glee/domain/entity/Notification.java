package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import com.cling.glee.domain.entity.enums.NotificationType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notifications")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type; // 알림 타입

    @Column
    private Long destinationUserId; // 알림 대상 user id

    @Column
    private Long sourceUserId; // 요청 user id

    @Column
    private Long questionId; // 질문

    @Column
    private Boolean isRead; // 읽음 여부

    @Column
    private Boolean isHide; // 숨김 여부

}
