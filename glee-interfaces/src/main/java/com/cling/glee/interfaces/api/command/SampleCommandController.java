package com.cling.glee.interfaces.api.command;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleCommandController {


	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/api/auth")
	@SecurityRequirement(name = "Authorization") // 인증 필요한 엔드포인트에 설정
	public String test() {
		return "security test";
	}

	// oauth 리다이렉션용 임시 엔드포인트
	@GetMapping("/login/oauth2/code/kakao")
	public String checkCode() {
		return "code";
	}

}