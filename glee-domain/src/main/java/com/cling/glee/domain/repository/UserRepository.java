package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByProviderId(String providerId);
}
