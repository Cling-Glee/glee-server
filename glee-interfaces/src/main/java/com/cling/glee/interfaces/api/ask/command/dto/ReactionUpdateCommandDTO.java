package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class ReactionUpdateCommandDTO {
    private String questionId;
    private String type;
}
