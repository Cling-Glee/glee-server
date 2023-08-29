package com.cling.glee.interfaces.api.command;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleCommandController {


	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@ApiOperation(value = "Private endpoint with JWT authorization", authorizations = @Authorization("Bearer"))
	@GetMapping("/api/auth")
	public String test() {
		return "security test";
	}

	// oauth 리다이렉션용 임시 엔드포인트
	@GetMapping("/login/oauth2/code/kakao")
	public String checkCode() {
		return "code";
	}

}