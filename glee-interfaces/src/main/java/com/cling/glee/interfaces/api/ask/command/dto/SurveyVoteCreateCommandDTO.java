package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class SurveyVoteCreateCommandDTO {
    private String surveyId;
    private Long surveyItemId;
}