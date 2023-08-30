package com.cling.glee.interfaces.api.oauth.command;


import com.cling.glee.domain.entity.redis.UserToken;
import com.cling.glee.domain.repository.UserTokenRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "테스트용")
@Slf4j
public class SampleCommandController {

	@Autowired
	UserTokenRepository redisTokenRepository;


	@GetMapping("/v1/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/v1/api/auth")
	@SecurityRequirement(name = "Authorization") // 인증 필요한 엔드포인트에 설정
	public String test() {
		return "security test";
	}

	// oauth 리다이렉션용 임시 엔드포인트
	@GetMapping("/login/oauth2/code/kakao")
	public String checkCode() {
		return "code";
	}

	// 레디스 테스트용
	@GetMapping("/token/{id}")
	public UserToken getPerson(@PathVariable Long id) {
		UserToken token = redisTokenRepository.findById(id).get();
		log.info("token: " + token);
		return token;
	}

	@PostMapping("/token")
	public UserToken getPerson(@RequestBody UserToken token) {
		UserToken savedToken = redisTokenRepository.save(token);
		log.info("savedToken: " + savedToken);
		return savedToken;
	}
}