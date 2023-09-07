package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SurveyUpdateCommandDTO {
    private String surveyId; // 설문 UUID
    private String endTime; // 종료 시간 변경 (YYYYMMDDHHMMSS)
    private String surveyContent; // 설문 내용 변경
    private Boolean isMultiSelect; // 다중선택 가능여부 변경
}
