package com.cling.glee.domain.service.command.impl;

import com.cling.glee.domain.entity.Question;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.repository.AnswerRepository;
import com.cling.glee.domain.repository.QuestionRepository;
import com.cling.glee.domain.repository.UserRepository;
import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.QuestionCreateVO;
import com.cling.glee.domain.service.vo.QuestionUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AskCommandServiceImpl implements AskCommandService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

    @Override
    public void registerQuestion(QuestionCreateVO questionCreateVO){
        Optional<User> answerUser = userRepository.findById(questionCreateVO.getAnswerUserId());
        if(answerUser.isPresent()){
            if(questionCreateVO.getIsMember()){
                // 회원 질문일 경우
                Optional<User> questionUser = userRepository.findById(questionCreateVO.getQuestionUserId());
                if(questionUser.isPresent()){
                    questionRepository.save(Question.builder()
                            .answerUser(answerUser.get())
                            .askUser(questionUser.get())
                            .isNickNameExposed(questionCreateVO.getIsNickNameExposed())
                            .questionContent(questionCreateVO.getQuestionContent())
                            .isHided(false)
                            .isDeleted(false)
                            .isMember(true)
                            .build());
                }
            }
            else if (!questionCreateVO.getIsMember()){
                // 비회원 질문일 경우
                questionRepository.save(Question.builder()
                        .answerUser(answerUser.get())
                        .isNickNameExposed(questionCreateVO.getIsNickNameExposed())
                        .questionContent(questionCreateVO.getQuestionContent())
                        .isHided(false)
                        .isDeleted(false)
                        .isMember(false)
                        .build());
            }
        }
    }

    @Override
    public void updateQuestion(QuestionUpdateVO questionUpdateVO) {
        Optional<Question> question = questionRepository.findById(questionUpdateVO.getQuestionId());
        if(question.isPresent()){
            Question updateQuestion = question.get();
            if(questionUpdateVO.getType().equals("HIDE")){
                // 질문 숨기기
                updateQuestion.setIsHided(questionUpdateVO.getIsActivated());
                questionRepository.save(updateQuestion);
            } else if(questionUpdateVO.getType().equals("FIXED")) {
                // 질문 상단 고정
                Optional<User> user = userRepository.findById(questionUpdateVO.getUserId());
                if(user.isPresent()){
                    User updateUser = user.get();
                    if(questionUpdateVO.getIsActivated()){
                        updateUser.setTopFixedQuestionId(questionUpdateVO.getQuestionId());
                    } else {
                        updateUser.setTopFixedQuestionId(null);
                    }
                    userRepository.save(updateUser);
                }
            }
        }
    }

    @Override
    public void deleteQuestion() {

    }
}
