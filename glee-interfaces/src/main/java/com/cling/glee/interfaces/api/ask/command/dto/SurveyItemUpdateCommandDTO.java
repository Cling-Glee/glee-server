package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SurveyItemUpdateCommandDTO {
    private String surveyId; // 설문 UUID
    private Long surveyItemId; // 설문 항목 ID
    private String surveyItemContent; // 설문 항목 내용
}
