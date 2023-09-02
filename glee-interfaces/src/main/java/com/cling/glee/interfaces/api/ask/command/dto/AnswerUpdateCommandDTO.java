package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class AnswerUpdateCommandDTO {
    private String questionId; // 질문 UUID
    private String answerContent; // 답변 내용
}
