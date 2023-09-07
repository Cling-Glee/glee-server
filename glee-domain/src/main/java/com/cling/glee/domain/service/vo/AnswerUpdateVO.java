package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class AnswerUpdateVO {
    private UUID questionUuid; // 질문 UUID
    private String answerContent; // 질문 내용
}
