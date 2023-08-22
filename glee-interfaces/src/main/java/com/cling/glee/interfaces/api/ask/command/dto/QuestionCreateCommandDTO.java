package com.cling.glee.interfaces.api.ask.command.dto;

import com.cling.glee.interfaces.api.common.UserBaseDTO;
import lombok.Data;

@Data
public class QuestionCreateCommandDTO extends UserBaseDTO {
    private String answerUser; // 타겟 유저 ID
    private String questionContent; // 질문 내용
    private Boolean isNickNameExposed; // 닉네임 공개 여부
}
