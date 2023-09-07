package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.SurveyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyItemRepository extends JpaRepository<SurveyItem, Long> {
}
