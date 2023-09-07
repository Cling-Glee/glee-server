package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ReactionDeleteVO {
    private UUID questionUuid;
    private UUID userUuid;
}
