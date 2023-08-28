package com.cling.glee.interfaces.api.command;

import com.cling.glee.auth.LoginResponse;
import com.cling.glee.auth.OAuth2Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class OAuthCommandController {

	private final OAuth2Service oAuth2Service;

	@GetMapping("/{provider}")
	public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestParam String code) {
		LoginResponse loginResponse = oAuth2Service.login(provider, code);
		return ResponseEntity.ok().body(loginResponse);
	}
}
