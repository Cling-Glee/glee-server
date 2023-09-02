package com.cling.glee.domain.service.command.impl;

import com.cling.glee.domain.entity.Survey;
import com.cling.glee.domain.entity.SurveyItem;
import com.cling.glee.domain.entity.SurveyVote;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.repository.SurveyItemRepository;
import com.cling.glee.domain.repository.SurveyRepository;
import com.cling.glee.domain.repository.SurveyVoteRepository;
import com.cling.glee.domain.repository.UserRepository;
import com.cling.glee.domain.service.command.SurveyCommandService;
import com.cling.glee.domain.service.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyCommandServiceImpl implements SurveyCommandService {

    private final SurveyRepository surveyRepository;

    private final SurveyItemRepository surveyItemRepository;

    private final SurveyVoteRepository surveyVoteRepository;

    private final UserRepository userRepository;

    // 설문 등록
    @Override
    public void registerSurvey(SurveyCreateVO surveyCreateVO){
        Optional<User> userOptional = userRepository.findByUuid(surveyCreateVO.getUserUuid());
        User user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }

        Survey survey = Survey.builder()
                .user(user)
                .surveyContent(surveyCreateVO.getSurveyContent())
                .endTime(surveyCreateVO.getEndTime())
                .isHide(false)
                .isDeleted(false)
                .isMultiSelect(surveyCreateVO.getIsMultiSelect())
                .surveyItems(surveyCreateVO.getItems().stream()
                        .map(x-> SurveyItem.builder()
                                .surveyItemContent(x)
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
        surveyRepository.save(survey);
    }

    // 설문 수정
    @Override
    public void updateSurvey(SurveyUpdateVO surveyUpdateVO){
        Optional<Survey> surveyOptional = surveyRepository.findByUuid(surveyUpdateVO.getSurveyUuid());
        if(surveyOptional.isPresent()){
            Survey survey = surveyOptional.get();
            if(surveyUpdateVO.getEndTime() != null){
                survey.setEndTime(survey.getEndTime());
            }
            if(surveyUpdateVO.getSurveyContent() != null){
                survey.setSurveyContent(surveyUpdateVO.getSurveyContent());
            }
            if(surveyUpdateVO.getIsMultiSelect() != null){
                survey.setIsMultiSelect(surveyUpdateVO.getIsMultiSelect());
            }
            surveyRepository.save(survey);
        }
    }

    // 설문 삭제
    @Override
    public void deleteSurvey(SurveyDeleteVO surveyDeleteVO){
        Optional<Survey> surveyOptional = surveyRepository.findByUuid(surveyDeleteVO.getSurveyUuid());
        if(surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            survey.setIsDeleted(true);
            surveyRepository.save(survey);
        }
    }

    // 설문 항목 추가
    @Override
    public void registerSurveyItem(SurveyItemCreateVO surveyItemCreateVO){
        Optional<Survey> surveyOptional = surveyRepository.findByUuid(surveyItemCreateVO.getSurveyUuid());
        if(surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();

            SurveyItem surveyItem = SurveyItem.builder()
                    .survey(survey)
                    .surveyItemContent(surveyItemCreateVO.getSurveyItemContent())
                    .build();
            surveyItemRepository.save(surveyItem);
        }
    }

    // 설문 항목 수정
    @Override
    public void updateSurveyItem(SurveyItemUpdateVO surveyItemUpdateVO){
        Optional<SurveyItem> surveyItemOptional = surveyItemRepository.findById(surveyItemUpdateVO.getSurveyItemId());
        if(surveyItemOptional.isPresent()){
            SurveyItem surveyItem = surveyItemOptional.get();
            surveyItem.setSurveyItemContent(surveyItem.getSurveyItemContent());
            surveyItemRepository.save(surveyItem);
        }
    }

    // 설문 항목 삭제
    @Override
    public void deleteSurveyItem(SurveyItemDeleteVO surveyItemDeleteVO){
        Optional<SurveyItem> surveyItemOptional = surveyItemRepository.findById(surveyItemDeleteVO.getSurveyItemId());
        if(surveyItemOptional.isPresent()){
            SurveyItem surveyItem = surveyItemOptional.get();
            surveyItemRepository.delete(surveyItem);
        }
    }

    // 설문 숨기기
    @Override
    public void hideSurvey(SurveyHideVO surveyHideVO){
        Optional<Survey> surveyOptional = surveyRepository.findByUuid(surveyHideVO.getSurveyUuid());
        if(surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            survey.setIsHide(true);
            surveyRepository.save(survey);
        }
    }

    // 설문 종료
    @Override
    public void endSurvey(SurveyEndVO surveyEndVO){
        Optional<Survey> surveyOptional = surveyRepository.findByUuid(surveyEndVO.getSurveyUuid());
        if(surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            survey.setEndTime(LocalDateTime.now());
            surveyRepository.save(survey);
        }
    }

    // 설문 응답 (by 방문자)
    @Override
    public void registerSurveyVote(SurveyVoteCreateVO surveyVoteCreateVO){
        Optional<SurveyItem> surveyItemOptional = surveyItemRepository.findById(surveyVoteCreateVO.getSurveyItemId());
        Optional<User> userOptional = userRepository.findByUuid(surveyVoteCreateVO.getUserUuid());
        if(surveyItemOptional.isPresent() && userOptional.isPresent()){
            SurveyVote surveyVote = SurveyVote.builder()
                    .surveyItem(surveyItemOptional.get())
                    .userId(userOptional.get().getId())
                    .build();
            surveyVoteRepository.save(surveyVote);
        }
    }

    // 설문 응답 수정 (단건 응답인 경우)
    @Override
    public void updateSurveyVote(SurveyVoteUpdateVO surveyVoteUpdateVO){
        Optional<Survey> surveyOptional = surveyRepository.findByUuid(surveyVoteUpdateVO.getSurveyUuid());
        Optional<User> userOptional = userRepository.findByUuid(surveyVoteUpdateVO.getUserUuid());
        if(surveyOptional.isPresent() && userOptional.isPresent()){
            Optional<SurveyVote> surveyVoteOptional = surveyVoteRepository.findBySurveyIdAndUserId(surveyOptional.get().getId(), userOptional.get().getId());
            Optional<SurveyItem> surveyItemOptional = surveyItemRepository.findById(surveyVoteUpdateVO.getSurveyItemId());
            if(surveyVoteOptional.isPresent() && surveyItemOptional.isPresent()){
                SurveyVote surveyVote = surveyVoteOptional.get();
                surveyVote.setSurveyItem(surveyItemOptional.get());
                surveyVoteRepository.save(surveyVote);
            }
        }
    }

    // 설문 응답 삭제
    @Override
    public void deleteSurveyVote(SurveyVoteDeleteVO surveyVoteDeleteVO){
        Optional<SurveyItem> surveyItemOptional = surveyItemRepository.findById(surveyVoteDeleteVO.getSurveyItemId());
        Optional<User> userOptional = userRepository.findByUuid(surveyVoteDeleteVO.getUserUuid());
        if(surveyItemOptional.isPresent() && userOptional.isPresent()){
            Optional<SurveyVote> surveyVote = surveyVoteRepository.findBySurveyItemAndUserId(surveyItemOptional.get(), userOptional.get().getId());
            if(surveyVote.isPresent()){
                surveyVoteRepository.delete(surveyVote.get());
            }
        }
    }

}
