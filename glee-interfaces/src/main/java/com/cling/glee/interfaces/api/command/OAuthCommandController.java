package com.cling.glee.interfaces.api.command;

import com.cling.glee.auth.OAuthService;
import com.cling.glee.auth.vo.user.KakaoUser;
import com.cling.glee.auth.vo.user.OAuthUser;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.service.query.impl.UserQueryServiceImpl;
import com.cling.glee.auth.vo.OAuthTokenVo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
		OAuthUser oAuthUser = null;
		if (provider.equals("kakao")) {
			oAuthUser = oAuthService.getKakaoUser(oAuthTokenVo.getAccess_token());
		}

		if (oAuthUser != null) {
			// oAuthUser 정보를 통해 DB에 사용자가 있는지 확인
			Optional<User> user = oAuthService.getUser(oAuthUser);

			// DB에 user 가 없다면 DB에 user 정보를 저장
			if (user.isEmpty()) {
				user = oAuthService.saveUser(oAuthUser);
			}

			log.info("user : {}", user);

//			// 사용자 정보를 통해 JWT 토큰을 발급
//			String token = oAuthService.createToken(user);
//
//			// 토큰을 프론트에게 전달
//			return ResponseEntity.ok(token);
		}





		return null;


	}
}
