package com.cling.glee.interfaces.api.ask.query;

import com.cling.glee.domain.service.query.AskQueryService;
import com.cling.glee.domain.service.vo.HideQuestionVO;
import com.cling.glee.domain.service.vo.NewQuestionVO;
import com.cling.glee.domain.service.vo.QuestionReadVO;
import com.cling.glee.domain.service.vo.RejectQuestionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/question")
@RequiredArgsConstructor
public class AuthQuestionQueryController {

    private final AskQueryService askQueryService;

    @GetMapping("/new")
    @Operation(summary = "새질문 조회")
    @SecurityRequirement(name = "Authorization")
    private List<NewQuestionVO> readNewQuestionList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getPrincipal().toString());

        return askQueryService.newQuestionList(QuestionReadVO.builder().userUuid(userId).build());
    }

    @GetMapping("/reject")
    @Operation(summary = "거절 질문 조회")
    @SecurityRequirement(name = "Authorization")
    private List<RejectQuestionVO> readRejectQuestionList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getPrincipal().toString());

        return askQueryService.rejectQuestionList(QuestionReadVO.builder().userUuid(userId).build());
    }

    @GetMapping("/hide")
    @Operation(summary = "숨김 질문 조회")
    @SecurityRequirement(name = "Authorization")
    private List<HideQuestionVO> readHideQuestionList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getPrincipal().toString());

        return askQueryService.hideQuestionList(QuestionReadVO.builder().userUuid(userId).build());
    }
}
