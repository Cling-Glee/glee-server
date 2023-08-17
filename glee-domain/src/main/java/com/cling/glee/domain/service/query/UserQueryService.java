package com.cling.glee.domain.service.query;

import com.cling.glee.domain.entity.User;

import java.util.Collection;

public interface UserQueryService {

	// 유저 한 명 조회
	User getUser(Long id);

	// 유저 전체 조회
	Iterable<User> getUsers();

}
