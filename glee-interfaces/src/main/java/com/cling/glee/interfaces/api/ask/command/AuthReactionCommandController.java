package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.domain.entity.enums.ReactionType;
import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.*;
import com.cling.glee.interfaces.api.ask.command.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/auth/reaction")
@RequiredArgsConstructor
public class AuthReactionCommandController {

    private final AskCommandService askCommandService;

    @PostMapping
    @Operation(summary = "공감 등록")
    @SecurityRequirement(name = "Authorization")
    public void reactionRegister(@RequestBody ReactionCreateCommandDTO reactionCreateCommandDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getPrincipal().toString());

        askCommandService.registerReaction(ReactionCreateVO.builder()
                        .reactionType(ReactionType.findByType(reactionCreateCommandDTO.getType()))
                        .userUuid(userId)
                        .questionUuid(UUID.fromString(reactionCreateCommandDTO.getQuestionId()))
                .build());
    }

    @PatchMapping
    @Operation(summary = "공감 수정")
    @SecurityRequirement(name = "Authorization")
    public void reactionUpdate(@RequestBody ReactionUpdateCommandDTO reactionUpdateCommandDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getPrincipal().toString());

        askCommandService.updateReaction(ReactionUpdateVO.builder()
                .reactionType(ReactionType.findByType(reactionUpdateCommandDTO.getType()))
                .userUuid(userId)
                .questionUuid(UUID.fromString(reactionUpdateCommandDTO.getQuestionId()))
                .build());
    }

    @DeleteMapping
    @Operation(summary = "공감 삭제")
    @SecurityRequirement(name = "Authorization")
    public void reactionUpdate(@RequestBody ReactionDeleteCommandDTO reactionDeleteCommandDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getPrincipal().toString());

        askCommandService.deleteReaction(ReactionDeleteVO.builder()
                .userUuid(userId)
                .questionUuid(UUID.fromString(reactionDeleteCommandDTO.getQuestionId()))
                .build());
    }
}
