package com.cling.glee.domain.service.query.impl;

import com.cling.glee.domain.entity.BlockUser;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserQueryServiceImplTest {

	@Autowired UserQueryServiceImpl userQueryService;
	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("유저 한 명 가져오기")
	void getUser() {
		// given
		User savedUser = userRepository.save(User.builder().build());

		// when
		User findUser = userQueryService.getUser(savedUser.getId());

		// then
		assertEquals(savedUser.getId(), findUser.getId());
	}

	@Test
	@DisplayName("유저 전체 가져오기")
	void getUsers() {
		// given

		User savedUser1 = userRepository.save(User.builder().build());
		User savedUser2 = userRepository.save(User.builder().build());

		// when
		List<User> users = userQueryService.getUsers();

		// then
		assertEquals(2, users.size());
	}
}