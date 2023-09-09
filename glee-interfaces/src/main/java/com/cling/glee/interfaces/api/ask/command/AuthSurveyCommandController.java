package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.domain.service.command.SurveyCommandService;
import com.cling.glee.domain.service.vo.*;
import com.cling.glee.interfaces.api.ask.command.dto.*;
import com.cling.glee.interfaces.util.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/auth/survey")
@RequiredArgsConstructor
public class AuthSurveyCommandController {

    private final SurveyCommandService surveyCommandService;
    private final AuthUtil authUtil;

    @PostMapping
    @Operation(summary = "설문 등록")
    @SecurityRequirement(name = "Authorization")
    private void surveyRegister(@RequestBody SurveyCreateCommandDTO surveyCreateCommandDTO){
        UUID userId = authUtil.getUuid();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime endTime = LocalDateTime.parse(surveyCreateCommandDTO.getEndTime(), formatter);

        surveyCommandService.registerSurvey(SurveyCreateVO.builder()
                        .userUuid(userId)
                        .surveyContent(surveyCreateCommandDTO.getSurveyContent())
                        .isMultiSelect(surveyCreateCommandDTO.getIsMultiSelect())
                        .endTime(endTime)
                        .items(surveyCreateCommandDTO.getItems())
                .build());
    }

    @PatchMapping
    @Operation(summary = "설문 수정")
    @SecurityRequirement(name = "Authorization")
    private void surveyUpdate(@RequestBody SurveyUpdateCommandDTO surveyUpdateCommandDTO){
        UUID userId = authUtil.getUuid();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime endTime = null;
        if(surveyUpdateCommandDTO.getEndTime() != null){
            endTime = LocalDateTime.parse(surveyUpdateCommandDTO.getEndTime(), formatter);
        }

        surveyCommandService.updateSurvey(SurveyUpdateVO.builder()
                .userUuid(userId)
                .surveyUuid(UUID.fromString(surveyUpdateCommandDTO.getSurveyId()))
                .endTime(endTime)
                .surveyContent(surveyUpdateCommandDTO.getSurveyContent())
                .isMultiSelect(surveyUpdateCommandDTO.getIsMultiSelect())
                .build());
    }

    @DeleteMapping
    @Operation(summary = "설문 삭제")
    @SecurityRequirement(name = "Authorization")
    private void surveyDelete(@RequestBody SurveyDeleteCommandDTO surveyDeleteCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.deleteSurvey(SurveyDeleteVO.builder()
                        .surveyUuid(UUID.fromString(surveyDeleteCommandDTO.getSurveyId()))
                        .userUuid(userId)
                .build());
    }

    @PostMapping("/item")
    @Operation(summary = "설문 항목 추가")
    @SecurityRequirement(name = "Authorization")
    private void surveyItemRegister(@RequestBody SurveyItemCreateCommandDTO surveyItemCreateCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.registerSurveyItem(SurveyItemCreateVO.builder()
                        .userUuid(userId)
                        .surveyUuid(UUID.fromString(surveyItemCreateCommandDTO.getSurveyId()))
                        .surveyItemContent(surveyItemCreateCommandDTO.getSurveyItemContent())
                .build());
    }

    @PatchMapping("/item")
    @Operation(summary = "설문 항목 수정")
    @SecurityRequirement(name = "Authorization")
    private void surveyItemUpdate(@RequestBody SurveyItemUpdateCommandDTO surveyItemUpdateCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.updateSurveyItem(SurveyItemUpdateVO.builder()
                        .surveyUuid(UUID.fromString(surveyItemUpdateCommandDTO.getSurveyId()))
                        .userUuid(userId)
                        .surveyItemId(surveyItemUpdateCommandDTO.getSurveyItemId())
                        .surveyItemContent(surveyItemUpdateCommandDTO.getSurveyItemContent())
                .build());
    }

    @DeleteMapping("/item")
    @Operation(summary = "설문 항목 삭제")
    @SecurityRequirement(name = "Authorization")
    private void surveyItemDelete(@RequestBody SurveyItemDeleteCommandDTO surveyItemDeleteCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.deleteSurveyItem(SurveyItemDeleteVO.builder()
                        .surveyUuid(UUID.fromString(surveyItemDeleteCommandDTO.getSurveyId()))
                        .userUuid(userId)
                        .surveyItemId(surveyItemDeleteCommandDTO.getSurveyItemId())
                .build());
    }

    @PatchMapping("/hide")
    @Operation(summary = "설문 숨기기")
    @SecurityRequirement(name = "Authorization")
    private void surveyHide(@RequestBody SurveyHideCommandDTO surveyHideCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.hideSurvey(SurveyHideVO.builder()
                        .userUuid(userId)
                        .surveyUuid(UUID.fromString(surveyHideCommandDTO.getSurveyId()))
                .build());
    }

    @PatchMapping("/end")
    @Operation(summary = "설문 종료")
    @SecurityRequirement(name = "Authorization")
    private void surveyEnd(@RequestBody SurveyEndCommandDTO surveyEndCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.endSurvey(SurveyEndVO.builder()
                        .userUuid(userId)
                        .surveyUuid(UUID.fromString(surveyEndCommandDTO.getSurveyId()))
                .build());
    }

    @PostMapping("/vote")
    @Operation(summary = "설문 투표 추가")
    @SecurityRequirement(name = "Authorization")
    private void surveyVoteRegister(@RequestBody SurveyVoteCreateCommandDTO surveyVoteCreateCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.registerSurveyVote(SurveyVoteCreateVO.builder()
                        .userUuid(userId)
                        .surveyUuid(UUID.fromString(surveyVoteCreateCommandDTO.getSurveyId()))
                        .surveyItemId(surveyVoteCreateCommandDTO.getSurveyItemId())
                .build());
    }

    @PatchMapping("/vote")
    @Operation(summary = "설문 투표 수정 (단건 투표 )")
    @SecurityRequirement(name = "Authorization")
    private void surveyVoteUpdate(@RequestBody SurveyVoteUpdateCommandDTO surveyVoteUpdateCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.updateSurveyVote(SurveyVoteUpdateVO.builder()
                        .userUuid(userId)
                        .surveyUuid(UUID.fromString(surveyVoteUpdateCommandDTO.getSurveyId()))
                        .surveyItemId(surveyVoteUpdateCommandDTO.getSurveyItemId())
                .build());
    }

    @DeleteMapping("/vote")
    @Operation(summary = "설문 투표 삭제")
    @SecurityRequirement(name = "Authorization")
    private void surveyVoteDelete(@RequestBody SurveyVoteDeleteCommandDTO surveyVoteDeleteCommandDTO){
        UUID userId = authUtil.getUuid();

        surveyCommandService.deleteSurveyVote(SurveyVoteDeleteVO.builder()
                        .userUuid(userId)
                        .surveyUuid(UUID.fromString(surveyVoteDeleteCommandDTO.getSurveyId()))
                        .surveyItemId(surveyVoteDeleteCommandDTO.getSurveyItemId())
                .build());
    }

}
