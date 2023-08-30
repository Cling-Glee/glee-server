package com.cling.glee.auth;

import com.cling.glee.auth.response.LoginResponse;
import com.cling.glee.auth.response.OAuth2TokenResponse;
import com.cling.glee.auth.userinfo.KakaoUserInfo;
import com.cling.glee.auth.userinfo.OAuth2UserInfo;
import com.cling.glee.domain.entity.User;
import com.cling.glee.domain.entity.enums.ProviderType;
import com.cling.glee.domain.entity.enums.Role;
import com.cling.glee.domain.entity.redis.UserToken;
import com.cling.glee.domain.repository.UserRepository;
import com.cling.glee.domain.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OAuth2Service {

	private final ClientRegistrationRepository clientRegistrationRepository;
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserTokenRepository userTokenRepository;


	@Transactional
	public LoginResponse login(String providerName, String code) {
		ClientRegistration provider = clientRegistrationRepository.findByRegistrationId(providerName);
		OAuth2TokenResponse tokenResponse = getToken(code, provider);

//        User user = getUserProfile(providerName, tokenResponse.getAccessToken(), provider);
		log.info("{}===> oauth access token", tokenResponse.getAccessToken());
		Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse.getAccessToken());
		OAuth2UserInfo oauth2UserInfo = null;
		log.info("providerName: {}", providerName);
		if (providerName.equals("kakao")) {
			oauth2UserInfo = new KakaoUserInfo(userAttributes);
		}
//		else if (providerName.equals("google")) {
//			oauth2UserInfo = new GoogleUserInfo(userAttributes);
//		}
		else {
			log.info("허용되지 않은 접근입니다.");
		}


		Optional<User> getUser = userRepository.findByEmailAndProviderType(oauth2UserInfo.getEmail(), oauth2UserInfo.getProviderType());

		User user = null;

		if (getUser.isEmpty()) {
			user = userRepository.save(User.builder()
					.nickname(oauth2UserInfo.getNickname())
					.email(oauth2UserInfo.getEmail())
					.providerId(oauth2UserInfo.getProviderId())
					.providerType(oauth2UserInfo.getProviderType())
					.role(Role.USER)
					.build())
			;
		} else {
			user = getUser.get();
		}

		String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(user.getId()));
		String refreshToken = jwtTokenProvider.createRefreshToken(String.valueOf(user.getId()));

		user.setRefreshToken(refreshToken);

		// redis 에 토큰 정보들 저장
		UserToken userToken = new UserToken();
		userToken.setUserId(user.getId());
		userToken.setKakaoAccessToken(tokenResponse.getAccessToken());
		userToken.setKakaoRefreshToken(tokenResponse.getRefreshToken());
		userToken.setOurRefreshToken(refreshToken);

		userTokenRepository.save(userToken);


		return LoginResponse.builder()
				.id(user.getId())
				.nickname(user.getNickname())
				.email(user.getEmail())
				.accessToken(accessToken)
				.refreshToken(user.getRefreshToken())
				.build();

	}

	private OAuth2TokenResponse getToken(String code, ClientRegistration provider) {

		return WebClient.create()
				.post()
				.uri(provider.getProviderDetails().getTokenUri())
				.headers(header -> {
					header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
					header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
				})
				.bodyValue(tokenRequest(code, provider))
				.retrieve()
				.bodyToMono(OAuth2TokenResponse.class)
				.block();


	}

	private MultiValueMap<String, String> tokenRequest(String code, ClientRegistration provider) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "authorization_code");
		formData.add("client_id", provider.getClientId());
		formData.add("redirect_uri", provider.getRedirectUri());
		formData.add("code", code);
		formData.add("client_secret", provider.getClientSecret());
		return formData;
	}

//    private User getUserProfile(String providerName, String oAuth2AccessToken, ClientRegistration provider){
//        Map<String, Object> userAttributes = getUserAttributes(provider, oAuth2AccessToken);
//        OAuth2UserInfo oauth2UserInfo = null;
//        log.info("providerName: {}", providerName);
//        if(providerName.equals("kakao")){
//            oauth2UserInfo = new KakaoUserInfo(userAttributes);
//        } else if (providerName.equals("google")){
//            oauth2UserInfo = new GoogleUserInfo(userAttributes);
//        } else {
//            log.info("허용되지 않은 접근입니다.");
//        }
//
//        String name = oauth2UserInfo.getName();
//        String email = oauth2UserInfo.getEmail();
//
//        Optional<User> user = userRepository.findByEmail(email);
//
//        if(user.isEmpty()){
//            return userRepository.save(User.builder()
//                    .name(name)
//                    .email(email)
//                    .role(Role.USER)
//                    .status(UserStatus.CREATED)
//                    .createdAt(new Date())
//                    .modifiedAt(new Date())
//                    .build())
//                    ;
//        } else {
//            return user.get();
//        }
//    }

	private Map<String, Object> getUserAttributes(ClientRegistration provider, String oAuth2AccessToken) {
		return WebClient.create()
				.get()
				.uri(provider.getProviderDetails().getUserInfoEndpoint().getUri())
				.headers(header -> header.setBearerAuth(oAuth2AccessToken))
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
				})
				.block();
	}

	public void logout(String providerName, Long userId) {
		if (Objects.equals(providerName, ProviderType.kakao.toString())) {
			kakaoUnlink(userId);
		}
	}

	// 카카오계정 연결끊기
	public void kakaoUnlink(Long userId) {

		// userId 로 redis 에서 토큰 정보 가져오기
		String kakaoAccessToken = userTokenRepository.findById(userId).get().getKakaoAccessToken();

		WebClient.create()
				.post()
				.uri("https://kapi.kakao.com/v1/user/unlink")
				.headers(header -> {
					header.setBearerAuth(kakaoAccessToken);
					header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				})
				.retrieve()
				.bodyToMono(Void.class)
				.block();
	}


}
