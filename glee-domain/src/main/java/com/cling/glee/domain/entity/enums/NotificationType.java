package com.cling.glee.domain.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    QUESTION("새질문"),
    ANSWER("답변 등록"),
    UPDATE_ANSWER("답변 수정"),
    CHAT("채팅 요청")
    ;

    private String description;

    public static ReactionTypeEnum findByType (String type){
        try {
            return ReactionTypeEnum.valueOf(type);
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}
