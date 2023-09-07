package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
public class QuestionCreateVO {
    private UUID questionUserUuid; // 질문 유저 ID
    private UUID answerUserUuid; // 타겟 유저 UUID
    private String questionContent; // 질문 내용
    private Boolean isNickNameExposed; // 닉네임 공개 여부
    private Boolean isMember; // 회원 여부
}
