package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.Survey;
import com.cling.glee.domain.entity.SurveyItem;
import com.cling.glee.domain.entity.SurveyVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyVoteRepository extends JpaRepository<SurveyVote, Long> {
    Optional<SurveyVote> findBySurveyItemAndUserId(SurveyItem surveyItem, Long userId);
    Optional<SurveyVote> findBySurveyIdAndUserId(Long surveyId, Long userId);
}
