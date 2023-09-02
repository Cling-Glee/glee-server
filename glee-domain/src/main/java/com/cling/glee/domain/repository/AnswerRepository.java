package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.Answer;
import com.cling.glee.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQuestion(Question question);
}
