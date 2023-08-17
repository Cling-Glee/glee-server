package com.cling.glee.domain.service.query.impl;

import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.repository.UserRepository;
import com.cling.glee.domain.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기전용 트랜잭션
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

	private final UserRepository userRepository;

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
