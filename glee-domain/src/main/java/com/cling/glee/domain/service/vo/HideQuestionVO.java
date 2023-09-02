package com.cling.glee.domain.service.vo;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class HideQuestionVO {
    private UUID questionUuid;
    private String questionContent; // 질문 내용
    private Boolean isCompleted; // 답변 여부
    private String answerContent; // 답변 내용
    private Reaction reaction; // 공감 수

    @Builder
    public static class Reaction {
        private Long like;
        private Long thumb;
        private Long check;
        private Long smile;
        private Long sad;
    }
}
