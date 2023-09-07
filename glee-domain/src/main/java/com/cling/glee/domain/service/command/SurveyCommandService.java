package com.cling.glee.domain.service.command;

import com.cling.glee.domain.service.vo.*;

public interface SurveyCommandService {

    // 설문 등록
    void registerSurvey(SurveyCreateVO surveyCreateVO);

    // 설문 수정
    void updateSurvey(SurveyUpdateVO surveyUpdateVO);

    // 설문 삭제
    void deleteSurvey(SurveyDeleteVO surveyDeleteVO);

    // 설문 항목 추가
    void registerSurveyItem(SurveyItemCreateVO surveyItemCreateVO);

    // 설문 항목 수정
    void updateSurveyItem(SurveyItemUpdateVO surveyItemUpdateVO);

    // 설문 항목 삭제
    void deleteSurveyItem(SurveyItemDeleteVO surveyItemDeleteVO);

    // 설문 숨기기
    void hideSurvey(SurveyHideVO surveyHideVO);

    // 설문 종료
    void endSurvey(SurveyEndVO surveyEndVO);

    // 설문 응답 (by 방문자)
    void registerSurveyVote(SurveyVoteCreateVO surveyVoteCreateVO);

    // 설문 응답 수정
    void updateSurveyVote(SurveyVoteUpdateVO surveyVoteUpdateVO);

    // 설문 응답 삭제
    void deleteSurveyVote(SurveyVoteDeleteVO surveyVoteDeleteVO);
}
