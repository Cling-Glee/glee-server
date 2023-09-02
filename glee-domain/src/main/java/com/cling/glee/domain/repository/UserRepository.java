package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.entity.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

	// select u from User u where u.email = ?1
	Optional<User> findByEmail(String email);

	// select u from User u where u.email = ?1 and u.providerType = ?2
	Optional<User> findByEmailAndProviderType(String email, ProviderType providerType);

	Optional<User> findByUuid(UUID uuid);

}
