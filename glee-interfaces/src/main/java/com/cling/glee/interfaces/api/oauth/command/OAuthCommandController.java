package com.cling.glee.interfaces.api.oauth.command;

import com.cling.glee.auth.LoginResponse;
import com.cling.glee.auth.OAuth2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "인증", description = "OAuth2")
public class OAuthCommandController {

	private final OAuth2Service oAuth2Service;

	@Operation(summary = "OAuth2 로그인 후 access token, refresh token 발급")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공"),
	})
	@GetMapping("/login/{provider}")
	public ResponseEntity<LoginResponse> login(
			@Parameter(description = "oauth 프로바이더 (ex. kakao)") @PathVariable String provider, @Parameter(description = "프로바이더 인증 코드") @RequestParam String code) {
		LoginResponse loginResponse = oAuth2Service.login(provider, code);
		return ResponseEntity.ok().body(loginResponse);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		return ResponseEntity.ok().body("logout");
	}
}
