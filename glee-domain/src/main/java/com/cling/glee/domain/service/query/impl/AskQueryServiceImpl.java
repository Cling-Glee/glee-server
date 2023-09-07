package com.cling.glee.domain.service.query.impl;

import com.cling.glee.domain.entity.AnswerReaction;
import com.cling.glee.domain.entity.Question;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.repository.AnswerReactionRepository;
import com.cling.glee.domain.repository.AnswerRepository;
import com.cling.glee.domain.repository.QuestionRepository;
import com.cling.glee.domain.repository.UserRepository;
import com.cling.glee.domain.service.query.AskQueryService;
import com.cling.glee.domain.service.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AskQueryServiceImpl implements AskQueryService {

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    private final AnswerReactionRepository answerReactionRepository;

    private final AnswerRepository answerRepository;

    @Override
    public List<NewQuestionVO> newQuestionList(QuestionReadVO questionReadVO){
        Optional<User> userOptional = userRepository.findByUuid(questionReadVO.getUserUuid());
        List<NewQuestionVO> newQuestionVOS = new ArrayList<>();
        if(userOptional.isPresent()){
            List<Question> questions = questionRepository.findAllByAnswerUserAndIsCompletedAndIsRejectAndIsHide(userOptional.get(), false, false, false);

            newQuestionVOS = questions.stream().map(x->NewQuestionVO.builder()
                            .questionUuid(x.getUuid())
                            .questionContent(x.getQuestionContent())
                            .build())
                    .collect(Collectors.toList());
        }
        return newQuestionVOS;
    }

    @Override
    public List<RejectQuestionVO> rejectQuestionList(QuestionReadVO questionReadVO){
        Optional<User> userOptional = userRepository.findByUuid(questionReadVO.getUserUuid());
        List<RejectQuestionVO> rejectQuestionVOS = new ArrayList<>();
        if(userOptional.isPresent()){
            List<Question> questions = questionRepository.findAllByAnswerUserAndIsReject(userOptional.get(), true);

            rejectQuestionVOS = questions.stream().map(x->RejectQuestionVO.builder()
                            .questionUuid(x.getUuid())
                            .questionContent(x.getQuestionContent())
                            .build())
                    .collect(Collectors.toList());
        }
        return rejectQuestionVOS;
    }

    @Override
    public List<HideQuestionVO> hideQuestionList(QuestionReadVO questionReadVO){
        Optional<User> userOptional = userRepository.findByUuid(questionReadVO.getUserUuid());
        List<HideQuestionVO> hideQuestionVOS = new ArrayList<>();
        if(userOptional.isPresent()){
            List<Question> questions = questionRepository.findALlByAnswerUserAndIsCompletedAndIsHide(userOptional.get(), true, true);

            hideQuestionVOS = questions.stream().map(x->{
                String answerContent = x.getAnswer().getAnswerContent();

                List<AnswerReaction> answerReactions = x.getAnswer().getAnswerReactions();
                long[] number = {0, 0, 0, 0, 0};
                for(int i=0;i<answerReactions.size();i++){
                    switch (answerReactions.get(i).getReactionType()){
                        case LIKE: number[0]++; break;
                        case THUMB: number[1]++; break;
                        case CHECK: number[2]++; break;
                        case SMILE: number[3]++; break;
                        case SAD: number[4]++; break;
                    }
                }
                HideQuestionVO.Reaction reaction = HideQuestionVO.Reaction.builder()
                        .like(number[0])
                        .thumb(number[1])
                        .check(number[2])
                        .smile(number[3])
                        .sad(number[4])
                        .build();

                return HideQuestionVO.builder()
                                .questionUuid(x.getUuid())
                                .questionContent(x.getQuestionContent())
                                .answerContent(answerContent)
                                .reaction(reaction)
                                .build();
                })
            .collect(Collectors.toList());
        }
        return hideQuestionVOS;
    }

    @Override
    public List<CompleteQuestionVO> completeQuestionList(QuestionReadVO questionReadVO){
        Optional<User> userOptional = userRepository.findByUuid(questionReadVO.getUserUuid());
        List<CompleteQuestionVO> completeQuestionVOS = new ArrayList<>();
        if(userOptional.isPresent()){
            List<Question> questions = questionRepository.findALlByAnswerUserAndIsCompletedAndIsHide(userOptional.get(), true,false);

            completeQuestionVOS = questions.stream().map(x->{
                        String answerContent = x.getAnswer().getAnswerContent();
                        List<AnswerReaction> answerReactions = x.getAnswer().getAnswerReactions();

                        long[] number = {0, 0, 0, 0, 0};
                        for(int i=0;i<answerReactions.size();i++){
                            switch (answerReactions.get(i).getReactionType()){
                                case LIKE: number[0]++; break;
                                case THUMB: number[1]++; break;
                                case CHECK: number[2]++; break;
                                case SMILE: number[3]++; break;
                                case SAD: number[4]++; break;
                            }
                        }
                        CompleteQuestionVO.Reaction reaction = CompleteQuestionVO.Reaction.builder()
                                .like(number[0])
                                .thumb(number[1])
                                .check(number[2])
                                .smile(number[3])
                                .sad(number[4])
                                .build();

                        return CompleteQuestionVO.builder()
                                .questionUuid(x.getUuid())
                                .questionContent(x.getQuestionContent())
                                .answerContent(answerContent)
                                .reaction(reaction)
                                .build();
                    })
                    .collect(Collectors.toList());
        }
        return completeQuestionVOS;
    }

}
