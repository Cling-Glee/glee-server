package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyCreateCommandDTO {
    private String surveyContent;
    private String endTime; // "YYYYMMDDHHMMSS"
    private Boolean isMultiSelect;
    private List<String> items;
}
