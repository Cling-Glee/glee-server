package com.cling.glee.domain.service.vo;

import lombok.Builder;

import java.util.UUID;

@Builder
public class NewQuestionVO {
    private UUID questionUuid;
    private String questionContent;
}
