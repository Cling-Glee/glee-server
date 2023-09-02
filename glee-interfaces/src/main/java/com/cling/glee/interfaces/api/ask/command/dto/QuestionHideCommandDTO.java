package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class QuestionHideCommandDTO {
    private String questionId; // 질문 UUID
    private Boolean isActivated; // 활성화 여부
}
