package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.*;
import com.cling.glee.interfaces.api.ask.command.dto.*;
import com.cling.glee.interfaces.util.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/v1/auth/question")
@RequiredArgsConstructor
public class AuthQuestionCommandController {

    private final AskCommandService askCommandService;
    private final AuthUtil authUtil;

    @PostMapping
    @Operation(summary = "질문 등록")
    @SecurityRequirement(name = "Authorization")
    public void questionRegister(@RequestBody QuestionCreateCommandDTO questionCreateCommandDTO){
        UUID userId = authUtil.getUuid();

        askCommandService.registerQuestion(QuestionCreateVO.builder()
                        .questionUserUuid(userId)
                        .answerUserUuid(UUID.fromString(questionCreateCommandDTO.getAnswerUserId()))
                        .questionContent(questionCreateCommandDTO.getQuestionContent())
                        .isNickNameExposed(questionCreateCommandDTO.getIsNickNameExposed())
                        .isMember(true)
                .build());
    }

    @PatchMapping("/hide")
    @Operation(summary = "질문 숨기기")
    @SecurityRequirement(name="Authorization")
    public void questionHide(@RequestBody QuestionHideCommandDTO questionHideCommandDTO){
        UUID userId = authUtil.getUuid();

        askCommandService.hideQuestion(QuestionHideVO.builder()
                        .questionUuid(UUID.fromString(questionHideCommandDTO.getQuestionId()))
                        .isActivated(questionHideCommandDTO.getIsActivated())
                        .userUuid(userId)
                .build());
    }

    @PatchMapping("/fixed")
    @Operation(summary = "질문 상단 고정")
    @SecurityRequirement(name="Authorization")
    public void questionFixed(@RequestBody QuestionFixedCommandDTO questionFixedCommandDTO){
        UUID userId = authUtil.getUuid();

        askCommandService.fixedQuestion(QuestionFixedVO.builder()
                    .questionUuid(UUID.fromString(questionFixedCommandDTO.getQuestionId()))
                    .isActivated(questionFixedCommandDTO.getIsActivated())
                    .userUuid(userId)
                .build());
    }

    @PatchMapping("/reject")
    @Operation(summary = "질문 거절")
    @SecurityRequirement(name="Authorization")
    public void questionReject(@RequestBody QuestionRejectCommandDTO questionRejectCommandDTO){
        UUID userId = authUtil.getUuid();

        askCommandService.rejectQuestion(QuestionRejectVO.builder()
                    .questionUuid(UUID.fromString(questionRejectCommandDTO.getQuestionId()))
                    .isActivated(questionRejectCommandDTO.getIsActivated())
                    .userUuid(userId)
                .build());
    }

    @DeleteMapping
    @Operation(summary = "질문 삭제")
    @SecurityRequirement(name="Authorization")
    public void questionDelete(@RequestBody QuestionDeleteCommandDTO questionDeleteCommandDTO){
        UUID userId = authUtil.getUuid();

        askCommandService.deleteQuestion(QuestionDeleteVO.builder()
                    .questionUuid(UUID.fromString(questionDeleteCommandDTO.getQuestionId()))
                    .userUuid(userId)
                .build());
    }
}
