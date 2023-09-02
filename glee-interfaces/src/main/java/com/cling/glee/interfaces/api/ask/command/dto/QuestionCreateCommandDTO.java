package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class QuestionCreateCommandDTO {
    private String answerUserId; // 타겟 유저 UUID
    private String questionContent; // 질문 내용
    private Boolean isNickNameExposed; // 닉네임 공개 여부
}
