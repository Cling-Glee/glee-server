package com.cling.glee.domain.service.command;

import com.cling.glee.domain.service.vo.*;

public interface AskCommandService {

    // 질문 등록
    void registerQuestion(QuestionCreateVO questionCreateVO);

    // 질문 숨기기
    void hideQuestion(QuestionHideVO questionHideVO);

    // 질문 상단 고정
    void fixedQuestion(QuestionFixedVO questionFixedVO);

    // 질문 거절
    void rejectQuestion(QuestionRejectVO questionRejectVO);

    // 질문 삭제
    void deleteQuestion(QuestionDeleteVO questionDeleteVO);

    // 답변 등록
    void registerAnswer(AnswerCreateVO answerCreateVO);

    // 답변 수정
    void updateAnswer(AnswerUpdateVO answerUpdateVO);

    // 공감 등록
    void registerReaction(ReactionCreateVO reactionCreateVO);

    // 공감 수정
    void updateReaction(ReactionUpdateVO reactionUpdateVO);

    // 공감 삭제
    void deleteReaction(ReactionDeleteVO reactionDeleteVO);

}
