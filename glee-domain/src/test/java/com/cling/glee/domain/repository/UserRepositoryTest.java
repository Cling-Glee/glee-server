package com.cling.glee.domain.repository;

import com.cling.glee.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserRepositoryTest {
	@Autowired UserRepository userRepository;

	@Test
	@DisplayName("유저 저장")
	public void userSave() throws Exception {
		//given
		User user = User.builder()
					.age(10)
					.email("abc@gmail.com")
					.build();

		//when
		User savedUser = userRepository.save(user);

		//then
		assertThat(savedUser.getId()).isEqualTo(user.getId());
		assertThat(savedUser.getAge()).isEqualTo(user.getAge());
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
	}
}