package com.cling.glee.interfaces.api.ask.query;

import com.cling.glee.domain.service.query.AskQueryService;
import com.cling.glee.domain.service.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionQueryController {

    private final AskQueryService askQueryService;

    @GetMapping("/complete/{userid}")
    @Operation(summary = "답변 완료 질문 조회")
    private List<CompleteQuestionVO> readCompleteQuestionList(@PathVariable("userid") String userId){
        return askQueryService.completeQuestionList(QuestionReadVO.builder().userUuid(UUID.fromString(userId)).build());
    }
}
