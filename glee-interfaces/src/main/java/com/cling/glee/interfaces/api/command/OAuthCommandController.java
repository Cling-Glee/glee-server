package com.cling.glee.interfaces.api.command;

import com.cling.glee.auth.OAuthService;
import com.cling.glee.auth.vo.user.KakaoUserVo;
import com.cling.glee.domain.service.query.impl.UserQueryServiceImpl;
import com.cling.glee.auth.vo.OAuthTokenVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class OAuthCommandController {

	private final UserQueryServiceImpl userQueryService;
	private final OAuthService oAuthService;

	// 프론트에서 넘어오는 provider와 code를 받아서 provider에게 access token 발급을 요청한다
	@GetMapping("/login/oauth/{provider}") // ex) /login/oauth/kakao
	public ResponseEntity login(@PathVariable String provider, @RequestParam String code) {

		// 넘어온 인가 코드를 통해 access token을 발급받는다
		OAuthTokenVo oAuthTokenVo = oAuthService.getAccessToken(provider, code);

		// 발급받은 access token을 통해 사용자 정보를 가져옴
		if (provider.equals("kakao")) {
			KakaoUserVo kakaoUser = oAuthService.getKakaoUser(oAuthTokenVo.getAccess_token());
		}

		return null;


	}
}
