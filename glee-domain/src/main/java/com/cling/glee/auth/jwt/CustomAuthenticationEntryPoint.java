package com.cling.glee.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final JwtProvider jwtProvider;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
	}
}
