package com.cling.glee.auth;

import com.cling.glee.auth.vo.OAuthTokenVo;
import com.cling.glee.auth.vo.user.KakaoUserVo;
import com.cling.glee.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true) // 읽기전용 트랜잭션
@RequiredArgsConstructor
@Slf4j
public class OAuthService {
	private final UserRepository userRepository;

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	public String kakaoClientId;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	public String kakaoRedirectUri;

	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	public String kakaoClientSecret;


	public OAuthTokenVo getAccessToken(String provider, String code) {

		log.info("kakaoclientId : {}", kakaoClientId);
		log.info("kakaoRedirectUri : {}", kakaoRedirectUri);

		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", kakaoRedirectUri);
		params.add("code", code);
		params.add("client_secret", kakaoClientSecret);

		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);

		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> accssTokenResponse = rt.postForEntity(
				"https://kauth.kakao.com/oauth/token",
				kakaoTokenRequest,
				String.class
		);

		log.info("accssTokenResponse : {}", accssTokenResponse);

		// json -> Object
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthTokenVo oAuthTokenVo = null;

		try {
			oAuthTokenVo = objectMapper.readValue(accssTokenResponse.getBody(), OAuthTokenVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("oAuthTokenVo : {}", oAuthTokenVo);


		return oAuthTokenVo;
	}

	// access token을 통해 카카오 사용자 정보를 가져온다
	public KakaoUserVo getKakaoUser(String token) {
		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoUserRequest =
				new HttpEntity<>(headers);

		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> kakaoUserResponse = rt.postForEntity(
				"https://kapi.kakao.com/v2/user/me",
				kakaoUserRequest,
				String.class
		);

		log.info("kakaoProfileResponse : {}", kakaoUserResponse);

		// json -> Object
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoUserVo kakaoUser = null;

		try {
			kakaoUser = objectMapper.readValue(kakaoUserResponse.getBody(), KakaoUserVo.class);
		} catch (Exception e) {
			// UnrecognizedPropertyException 예외처리 해주기
			e.printStackTrace();
		}

		log.info("kakaoUser : {}", kakaoUser);

		return kakaoUser;
	}
}
