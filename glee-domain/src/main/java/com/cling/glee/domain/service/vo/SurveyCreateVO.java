package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class SurveyCreateVO {
    private UUID userUuid;
    private String surveyContent;
    private LocalDateTime endTime;
    private Boolean isMultiSelect;
    private List<String> items;
}
