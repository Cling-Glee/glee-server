package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.QuestionCreateVO;
import com.cling.glee.domain.service.vo.QuestionUpdateVO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionCreateCommandDTO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionDeleteCommandDTO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionUpdateCommandDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member/question")
@RequiredArgsConstructor
public class MemberQuestionCommandController {
    //TODO : Post 만

    private final AskCommandService askCommandService;

    // 질문 등록
    @PostMapping("/register")
    public void questionRegister(@RequestBody QuestionCreateCommandDTO questionCreateCommandDTO){
        askCommandService.registerQuestion(QuestionCreateVO.builder()
                        .questionUser(questionCreateCommandDTO.getUserId())
                        .answerUser(questionCreateCommandDTO.getAnswerUser())
                        .questionContent(questionCreateCommandDTO.getQuestionContent())
                        .isNickNameExposed(questionCreateCommandDTO.getIsNickNameExposed())
                        .isMember(true)
                .build());
    }

    // 질문 수정
    @PostMapping("/update")
    public void questionUpdate(@RequestBody QuestionUpdateCommandDTO questionUpdateCommandDTO){
        askCommandService.updateQuestion(QuestionUpdateVO.builder()
                        .type(questionUpdateCommandDTO.getType())
                        .questionId(questionUpdateCommandDTO.getQuestionId())
                        .isActivated(questionUpdateCommandDTO.getIsActivated())
                        .userId(questionUpdateCommandDTO.getUserId())
                .build());
    }

    // 질문 삭제
    @PostMapping("/delete")
    public void questionDelete(@RequestBody QuestionDeleteCommandDTO questionDeleteCommandDTO){

    }

}
