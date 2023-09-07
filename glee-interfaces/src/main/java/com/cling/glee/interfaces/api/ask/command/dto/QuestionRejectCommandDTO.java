package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class QuestionRejectCommandDTO {
    private String questionId; // 질문 UUID
    private Boolean isActivated;
}
