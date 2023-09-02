package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findById(Long id);
    Optional<Question> findByUuid(UUID uuid);
}
