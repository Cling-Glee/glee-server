package com.cling.glee.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OAuth2Config {

	private final Environment env;

	private static final String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration";


	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {

		List<ClientRegistration> registrations = new ArrayList<>();
//		registrations.add(this.googleClientRegistration());
		registrations.add(this.kakaoClientRegistration());

		return new InMemoryClientRegistrationRepository(registrations);
	}

//	private ClientRegistration googleClientRegistration() {
//		log.info("====googleClientRegistration====");
//		return ClientRegistration.withRegistrationId("google")
//				.clientId(env.getProperty(CLIENT_PROPERTY_KEY + ".google.client-id"))
//				.clientSecret(env.getProperty(CLIENT_PROPERTY_KEY + ".google.client-secret"))
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.redirectUri(env.getProperty(CLIENT_PROPERTY_KEY + ".google.redirect-uri"))
////                .scope(env.getProperty(CLIENT_PROPERTY_KEY + ".google.scope"))
//				.scope("profile", "email")
//				.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//				.tokenUri("https://www.googleapis.com/oauth2/v4/token")
//				.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//				.userNameAttributeName(IdTokenClaimNames.SUB)
//				.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//				.clientName("Google")
//				.build();
//	}

	private ClientRegistration kakaoClientRegistration() {
		log.info("====kakaoClientRegistration====");
		return ClientRegistration.withRegistrationId("kakao")
				.clientId(env.getProperty(CLIENT_PROPERTY_KEY + ".kakao.client-id"))
				.clientSecret(env.getProperty(CLIENT_PROPERTY_KEY + ".kakao.client-secret"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri(env.getProperty(CLIENT_PROPERTY_KEY + ".kakao.redirect-uri"))
				.scope("profile", "account_email")
				.authorizationUri("https://kauth.kakao.com/oauth/authorize")
				.tokenUri("https://kauth.kakao.com/oauth/token")
				.userInfoUri("https://kapi.kakao.com/v2/user/me")
				.userNameAttributeName(IdTokenClaimNames.SUB)
				.clientName("kakao")
				.build();
	}
}
