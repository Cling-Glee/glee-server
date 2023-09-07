package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.QuestionCreateVO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionCreateCommandDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionCommandController {

    private final AskCommandService askCommandService;

    // 질문 등록 (비회원)
    @PostMapping
    @Operation(summary = "(비회원) 질문 등록")
    public void questionRegister(@RequestBody QuestionCreateCommandDTO questionCreateCommandDTO){
        askCommandService.registerQuestion(QuestionCreateVO.builder()
                .answerUserUuid(UUID.fromString(questionCreateCommandDTO.getAnswerUserId()))
                .questionContent(questionCreateCommandDTO.getQuestionContent())
                .isMember(false)
                .build());
    }
}
