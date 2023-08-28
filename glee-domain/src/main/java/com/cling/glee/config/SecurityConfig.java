package com.cling.glee.config;

import com.cling.glee.auth.jwt.CustomAuthenticationEntryPoint;
import com.cling.glee.auth.jwt.JwtRequestFilter;
import com.cling.glee.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final UserRepository userRepository;
	private final CorsConfig corsConfig;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private final JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				.and()
				.httpBasic().disable()
				.formLogin().disable()
				.addFilter(corsConfig.corsFilter());

		http.authorizeRequests()
				.antMatchers("/hello/**")
				.authenticated()
				.anyRequest().permitAll()

				.and()
				.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
