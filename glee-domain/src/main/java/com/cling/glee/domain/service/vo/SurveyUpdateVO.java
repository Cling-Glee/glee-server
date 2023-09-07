package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class SurveyUpdateVO {
    private UUID userUuid;
    private UUID surveyUuid;
    private LocalDateTime endTime; // 종료 시간 변경
    private String surveyContent; // 설문 내용 변경
    private Boolean isMultiSelect; // 다중선택 가능여부 변경
}
