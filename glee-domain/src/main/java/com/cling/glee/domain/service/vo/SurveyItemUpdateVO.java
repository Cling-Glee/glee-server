package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class SurveyItemUpdateVO {
    private UUID userUuid;
    private UUID surveyUuid;
    private Long surveyItemId;
    private String surveyItemContent;
}
