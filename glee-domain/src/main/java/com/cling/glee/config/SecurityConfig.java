package com.cling.glee.config;

import com.cling.glee.auth.JwtRequestFilter;
import com.cling.glee.auth.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


	private final Environment env;
	private final CorsConfig corsConfig;
	private final JwtTokenProvider jwtTokenProvider;


	private static final String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration";

	ObjectMapper objectMapper = new ObjectMapper();


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("filterChain: {}", http);
		http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.cors().configurationSource(corsConfig.corsConfigurationSource());


		http
				.authorizeRequests() // URL 별 권한 접근제어 관리 시작점
				.antMatchers("/api/auth/**").authenticated() // /api/auth 엔드포인트는 인증된 사용자에게만 공개
				.anyRequest().permitAll() // 나머지 요청은 모두 허용
		;

		http
				.addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);


		return http.build();
	}


}