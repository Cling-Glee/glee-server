package com.cling.glee.domain.service.vo;

import com.cling.glee.domain.entity.enums.ReactionType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ReactionCreateVO {
    private ReactionType reactionType;
    private UUID questionUuid;
    private UUID userUuid;
}
