package com.cling.glee.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

	@Test
	void addBlockUser() {
		User user = new User();
		BlockUser blockUser = new BlockUser();

		// builder 를 사용하면 nullpointerexception 이 발생한다.
		// https://bbeomgeun.tistory.com/174
		// 그래서 그냥 엔티티 기본생성자 public 열어놨음

		user.addBlockUser(blockUser);
		System.out.println("blockUser = " + blockUser.getUser());
		System.out.println("user = " + user);

		assertEquals(blockUser.getUser(), user);
	}

//	@Test
//	void removeBlockUser() {
//	}
//
//	@Test
//	void askQuestion() {
//	}
//
//	@Test
//	void answerQuestion() {
//	}
//
//	@Test
//	void removeQuestion() {
//	}
}