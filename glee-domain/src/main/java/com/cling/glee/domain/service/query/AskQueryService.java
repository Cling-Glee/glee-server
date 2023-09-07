package com.cling.glee.domain.service.query;

import com.cling.glee.domain.service.vo.*;

import java.util.List;

public interface AskQueryService {

    // 새질문 조회
    List<NewQuestionVO> newQuestionList(QuestionReadVO questionReadVO);

    // 거절 질문 조회
    List<RejectQuestionVO> rejectQuestionList(QuestionReadVO questionReadVO);

    // 숨김 질문 조회
    List<HideQuestionVO> hideQuestionList(QuestionReadVO questionReadVO);

    // 답변 완료 질문 조회
    List<CompleteQuestionVO> completeQuestionList(QuestionReadVO questionReadVO);

}