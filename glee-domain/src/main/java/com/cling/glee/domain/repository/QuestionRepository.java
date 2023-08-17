package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
