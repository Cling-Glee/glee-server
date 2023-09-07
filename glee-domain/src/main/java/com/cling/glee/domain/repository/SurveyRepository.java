package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Optional<Survey> findByUuid(UUID uuid);
}
