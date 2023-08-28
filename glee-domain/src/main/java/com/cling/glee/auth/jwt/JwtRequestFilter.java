package com.cling.glee.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		System.out.println("JwtRequestFilter 진입");

		String requestHeader = request.getHeader(jwtProvider.getHEADER_STRING());


		// header 가 null 이거나 Bearer 로 시작하지 않으면 필터링하지 않음
		if (requestHeader == null || !requestHeader.startsWith(jwtProvider.getTOKEN_PREFIX())) {
			filterChain.doFilter(request, response);
			return;
		}

		/* jwt 토큰을 검증해서 정상적인 사용자인지 확인 */

		// Bearer 제거
		String token = requestHeader.replace(jwtProvider.getTOKEN_PREFIX(), "");

		boolean isValidated = jwtProvider.validateToken(token);

		if (!isValidated) {
			filterChain.doFilter(request, response);
			return;
		}

		Claims payload = jwtProvider.getPayload(token);

		log.info("payload : {}", payload);


		request.setAttribute("userId", payload.get("userId"));

		filterChain.doFilter(request, response);
	}
}
