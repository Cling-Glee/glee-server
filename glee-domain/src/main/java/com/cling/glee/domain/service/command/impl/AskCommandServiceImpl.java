package com.cling.glee.domain.service.command.impl;

import com.cling.glee.domain.entity.Answer;
import com.cling.glee.domain.entity.AnswerReaction;
import com.cling.glee.domain.entity.Question;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.repository.AnswerReactionRepository;
import com.cling.glee.domain.repository.AnswerRepository;
import com.cling.glee.domain.repository.QuestionRepository;
import com.cling.glee.domain.repository.UserRepository;
import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AskCommandServiceImpl implements AskCommandService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

    private final AnswerReactionRepository answerReactionRepository;

    @Override
    public void registerQuestion(QuestionCreateVO questionCreateVO){
        Optional<User> answerUser = userRepository.findByUuid(questionCreateVO.getAnswerUserUuid());
        if(answerUser.isPresent()){
            if(questionCreateVO.getIsMember()){
                // 회원 질문일 경우
                Optional<User> questionUser = userRepository.findByUuid(questionCreateVO.getQuestionUserUuid());
                if(questionUser.isPresent()){
                    questionRepository.save(Question.builder()
                            .answerUser(answerUser.get())
                            .askUser(questionUser.get())
                            .isNickNameExposed(questionCreateVO.getIsNickNameExposed())
                            .questionContent(questionCreateVO.getQuestionContent())
                            .isHide(false)
                            .isDeleted(false)
                            .isMember(true)
                            .isReject(false)
                            .isCompleted(false)
                            .build());
                }
            } else if (!questionCreateVO.getIsMember()){
                // 비회원 질문일 경우
                questionRepository.save(Question.builder()
                        .answerUser(answerUser.get())
                        .isNickNameExposed(questionCreateVO.getIsNickNameExposed())
                        .questionContent(questionCreateVO.getQuestionContent())
                        .isHide(false)
                        .isDeleted(false)
                        .isMember(false)
                        .isReject(false)
                        .isCompleted(false)
                        .build());
            }
        }
    }

    @Override
    public void hideQuestion(QuestionHideVO questionHideVO){
        Optional<User> user = userRepository.findByUuid(questionHideVO.getUserUuid());
        Optional<Question> question = questionRepository.findByUuid(questionHideVO.getQuestionUuid());
        if(user.isPresent() && question.isPresent()){
            Question updateQuestion = question.get();
            updateQuestion.setIsHide(questionHideVO.getIsActivated());
            questionRepository.save(updateQuestion);
        }
    }

    @Override
    public void fixedQuestion(QuestionFixedVO questionFixedVO){
        Optional<User> user = userRepository.findByUuid(questionFixedVO.getUserUuid());
        Optional<Question> question = questionRepository.findByUuid(questionFixedVO.getQuestionUuid());
        if(user.isPresent() && question.isPresent()){
            User updateUser = user.get();
            if(questionFixedVO.getIsActivated()){
                updateUser.setTopFixedQuestionId(question.get().getId());
            } else {
                updateUser.setTopFixedQuestionId(null);
            }
            userRepository.save(updateUser);
        }
    }

    @Override
    public void rejectQuestion(QuestionRejectVO questionRejectVO){
        Optional<User> user = userRepository.findByUuid(questionRejectVO.getUserUuid());
        Optional<Question> question = questionRepository.findByUuid(questionRejectVO.getQuestionUuid());
        if(user.isPresent() && question.isPresent()){
            Question updateQuestion = question.get();
            updateQuestion.setIsReject(questionRejectVO.getIsActivated());
            questionRepository.save(updateQuestion);
        }
    }

    @Override
    public void deleteQuestion(QuestionDeleteVO questionDeleteVO){
        Optional<User> user = userRepository.findByUuid(questionDeleteVO.getUserUuid());
        Optional<Question> question = questionRepository.findByUuid(questionDeleteVO.getQuestionUuid());
        if(user.isPresent() && question.isPresent()){
            Question updateQuestion = question.get();
            updateQuestion.setIsDeleted(questionDeleteVO.getIsActivated());
            questionRepository.save(updateQuestion);
        }
    }

    @Override
    public void registerAnswer(AnswerCreateVO answerCreateVO){
        Optional<Question> question = questionRepository.findByUuid(answerCreateVO.getQuestionUuid());
        if(question.isPresent()){
            Question updateQuestion = question.get();
            Answer answer = Answer.builder()
                    .question(updateQuestion)
                    .answerContent(answerCreateVO.getAnswerContent())
                    .isDeleted(false)
                    .build();
            updateQuestion.setIsCompleted(true);

            answerRepository.save(answer);
            questionRepository.save(updateQuestion);
        }
    }

    @Override
    public void updateAnswer(AnswerUpdateVO answerUpdateVO){
        Optional<Question> question = questionRepository.findByUuid(answerUpdateVO.getQuestionUuid());
        if(question.isPresent()){
            Question updateQuestion = question.get();
            Optional<Answer> answer = answerRepository.findByQuestion(updateQuestion);
            if(answer.isPresent()){
                Answer updateAnswer = answer.get();
                updateAnswer.setAnswerContent(answerUpdateVO.getAnswerContent());
                answerRepository.save(updateAnswer);
            }
        }
    }

    @Override
    public void registerReaction(ReactionCreateVO reactionCreateVO){
        // TODO : 리팩토링 필요
        Optional<Question> question = questionRepository.findByUuid(reactionCreateVO.getQuestionUuid());
        if(question.isPresent()){
            Optional<Answer> answer = answerRepository.findByQuestion(question.get());
            Optional<User> user = userRepository.findByUuid(reactionCreateVO.getUserUuid());
            if(answer.isPresent() && user.isPresent()){
                AnswerReaction answerReaction = AnswerReaction.builder()
                        .reactionType(reactionCreateVO.getReactionType())
                        .answer(answer.get())
                        .userId(user.get().getId())
                        .build();
                answerReactionRepository.save(answerReaction);
            }
        }
    }

    @Override
    public void updateReaction(ReactionUpdateVO reactionUpdateVO){
        // TODO : 리팩토링 필요
        Optional<Question> question = questionRepository.findByUuid(reactionUpdateVO.getQuestionUuid());
        Optional<User> user = userRepository.findByUuid(reactionUpdateVO.getUserUuid());

        if(question.isPresent() && user.isPresent()){
            Optional<Answer> answer = answerRepository.findByQuestion(question.get());
            if(answer.isPresent()){
                Optional<AnswerReaction> answerReaction = answerReactionRepository.findByAnswerAndUserId(answer.get(), user.get().getId());

                if(answerReaction.isPresent()){
                    AnswerReaction updateAnswerReaction = answerReaction.get();
                    updateAnswerReaction.setReactionType(reactionUpdateVO.getReactionType());
                    answerReactionRepository.save(updateAnswerReaction);
                }
            }
        }
    }

    @Override
    public void deleteReaction(ReactionDeleteVO reactionDeleteVO){
        Optional<Question> question = questionRepository.findByUuid(reactionDeleteVO.getQuestionUuid());
        Optional<User> user = userRepository.findByUuid(reactionDeleteVO.getUserUuid());

        if(question.isPresent() && user.isPresent()){
            Optional<Answer> answer = answerRepository.findByQuestion(question.get());
            if(answer.isPresent()){
                Optional<AnswerReaction> answerReaction = answerReactionRepository.findByAnswerAndUserId(answer.get(), user.get().getId());

                if(answerReaction.isPresent()){
                    answerReactionRepository.delete(answerReaction.get());
                }
            }
        }
    }
}
