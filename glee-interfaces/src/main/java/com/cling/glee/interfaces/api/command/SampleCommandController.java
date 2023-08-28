package com.cling.glee.interfaces.api.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleCommandController {


	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	// oauth 리다이렉션용 임시 엔드포인트
	@GetMapping("/login/oauth2/code/kakao")
	public String checkCode() {
		return "code";
	}


}