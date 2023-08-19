package com.cling.glee.domain.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReactionTypeEnum {
    LIKE("좋아요"),
    LOVE("하트"),
    HAHA("웃음"),
    WOW("놀람"),
    SAD("슬픔"),
    ANGRY("화남")
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
