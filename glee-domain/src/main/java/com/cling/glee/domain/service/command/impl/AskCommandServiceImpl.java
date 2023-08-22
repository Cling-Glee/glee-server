package com.cling.glee.domain.service.command.impl;

import com.cling.glee.domain.entity.Question;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.repository.AnswerRepository;
import com.cling.glee.domain.repository.QuestionRepository;
import com.cling.glee.domain.repository.UserRepository;
import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.QuestionCreateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AskCommandServiceImpl implements AskCommandService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

    @Override
    public void registerQuestion(QuestionCreateVO questionCreateVO){
        User AnswerUser = userRepository.findByProviderId(questionCreateVO.getAnswerUser());

        if(questionCreateVO.getIsMember()){
            // 회원 질문일 경우
            User QuestionUser = userRepository.findByProviderId(questionCreateVO.getQuestionUser());
            questionRepository.save(Question.builder()
                            .answerUser(AnswerUser)
                            .askUser(QuestionUser)
                            .isNickNameExposed(questionCreateVO.getIsNickNameExposed())
                            .questionContent(questionCreateVO.getQuestionContent())
                            .isHided(false)
                            .isDeleted(false)
                            .isMember(true)
                    .build());

        } else {
            // 비회원 질문일 경우
            questionRepository.save(Question.builder()
                    .answerUser(AnswerUser)
                    .isNickNameExposed(questionCreateVO.getIsNickNameExposed())
                    .questionContent(questionCreateVO.getQuestionContent())
                    .isHided(false)
                    .isDeleted(false)
                    .isMember(false)
                    .build());
        }
    }

    @Override
    public void updateQuestion() {

    }

    @Override
    public void deleteQuestion() {

    }
}
