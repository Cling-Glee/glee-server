package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.AnswerCreateVO;
import com.cling.glee.domain.service.vo.AnswerUpdateVO;
import com.cling.glee.interfaces.api.ask.command.dto.AnswerCreateCommandDTO;
import com.cling.glee.interfaces.api.ask.command.dto.AnswerUpdateCommandDTO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionCreateCommandDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/auth/answer")
@RequiredArgsConstructor
public class AuthAnswerCommandController {

    private final AskCommandService askCommandService;

    @PostMapping
    @Operation(summary = "답변 등록")
    @SecurityRequirement(name = "Authorization")
    public void answerRegister(@RequestBody AnswerCreateCommandDTO answerCreateCommandDTO){
        askCommandService.registerAnswer(AnswerCreateVO.builder()
                        .questionUuid(UUID.fromString(answerCreateCommandDTO.getQuestionId()))
                        .answerContent(answerCreateCommandDTO.getAnswerContent())
                .build());
    }

    @PatchMapping
    @Operation(summary = "답변 수정")
    @SecurityRequirement(name = "Authorization")
    public void answerUpdate(@RequestBody AnswerUpdateCommandDTO answerUpdateCommandDTO){
        askCommandService.updateAnswer(AnswerUpdateVO.builder()
                .questionUuid(UUID.fromString(answerUpdateCommandDTO.getQuestionId()))
                .answerContent(answerUpdateCommandDTO.getAnswerContent())
                .build());
    }
}
