package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class SurveyItemDeleteCommandDTO {
    private String surveyId; // 설문 UUID
    private Long surveyItemId; // 설문 항목 ID
}
