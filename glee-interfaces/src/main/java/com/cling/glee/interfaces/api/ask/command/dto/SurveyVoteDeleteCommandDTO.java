package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class SurveyVoteDeleteCommandDTO {
    private String surveyId;
    private Long surveyItemId;
}
