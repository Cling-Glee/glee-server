package com.cling.glee.domain.service.command;

import com.cling.glee.domain.service.vo.QuestionCreateVO;
import com.cling.glee.domain.service.vo.QuestionUpdateVO;

public interface AskCommandService {

    // 질문 등록
    void registerQuestion(QuestionCreateVO questionCreateVO);

    // 질문 수정
    void updateQuestion(QuestionUpdateVO questionUpdateVO);

    // 질문 삭제
    void deleteQuestion();

}
