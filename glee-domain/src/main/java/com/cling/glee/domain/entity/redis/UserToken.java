package com.cling.glee.domain.entity.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 30) // value 값이 redis key의 prefix가 된다.
// key = token:<userId>
public class UserToken {

	@Id
	private Long userId;
	private String kakaoAccessToken;
	private String kakaoRefreshToken;
	private String ourRefreshToken;

}
