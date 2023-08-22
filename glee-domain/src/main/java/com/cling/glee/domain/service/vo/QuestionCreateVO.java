package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class QuestionCreateVO {
    private String questionUser; // 질문 유저 ID
    private String answerUser; // 타겟 유저 ID
    private String questionContent; // 질문 내용
    private Boolean isNickNameExposed; // 닉네임 공개 여부
    private Boolean isMember; // 회원 여부
}
