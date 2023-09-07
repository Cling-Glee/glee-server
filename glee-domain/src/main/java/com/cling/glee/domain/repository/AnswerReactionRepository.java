package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.Answer;
import com.cling.glee.domain.entity.AnswerReaction;
import com.cling.glee.domain.entity.enums.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerReactionRepository extends JpaRepository<AnswerReaction, Long> {
    Optional<AnswerReaction> findByAnswerAndUserId(Answer answer, Long userId);
}
