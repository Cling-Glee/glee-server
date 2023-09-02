package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.redis.UserToken;
import org.springframework.data.repository.CrudRepository;

// redis/UserToken
public interface UserTokenRepository extends CrudRepository<UserToken, Long> {
}
