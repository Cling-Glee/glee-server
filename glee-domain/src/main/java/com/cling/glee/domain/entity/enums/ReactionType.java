package com.cling.glee.domain.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReactionType {
    LIKE("좋아요"),
    THUMB("엄지"),
    CHECK("체크"),
    SMILE("웃음"),
    SAD("슬픔");


    private String description;

    public static ReactionType findByType (String type){
        try {
            return ReactionType.valueOf(type);
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}
