package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class SurveyItemCreateCommandDTO {
    private String surveyId; // 설문 UUID
    private String surveyItemContent; // 설문 항목 내용
}
