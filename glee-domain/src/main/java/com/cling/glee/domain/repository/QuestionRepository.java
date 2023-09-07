package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.Question;
import com.cling.glee.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findById(Long id);
    Optional<Question> findByUuid(UUID uuid);
    List<Question> findAllByAnswerUserAndIsCompletedAndIsRejectAndIsHide(User user, Boolean isCompleted, Boolean isReject, Boolean isHide);
    List<Question> findAllByAnswerUserAndIsReject(User user, Boolean isReject);
//    List<Question> findAllByAnswerUserAndIsHide(User user, Boolean isHide);
    List<Question> findALlByAnswerUserAndIsCompletedAndIsHide(User user, Boolean isCompleted, Boolean isHide);
}
