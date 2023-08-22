package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionUpdateVO {
    private String type; // HIDE(숨기기), FIXED(상단 고정)
    private Long questionId;
    private Boolean isActivated;
    private String userId;
}
