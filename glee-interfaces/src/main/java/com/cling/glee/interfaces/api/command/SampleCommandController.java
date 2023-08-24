package com.cling.glee.interfaces.api.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleCommandController {


	@Value("${spring.jpa.open-in-view}")
	private String openInView;

	@Value("${jwt.secret}")
	private String secret;


	@GetMapping("/hello")
	public String hello() {
		return "hello from " + " secret: " + secret;
	}

	@GetMapping("/login/oauth2/code/kakao")
	public String checkCode() {
		return "code";
	}


}