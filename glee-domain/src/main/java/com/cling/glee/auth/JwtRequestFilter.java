package com.cling.glee.auth;

import com.cling.glee.domain.entity.enums.Role;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");

		// 헤더에 토큰 안 담기는 경우는 그대로 진행
		// 추후 수정 필요?
		if (authorizationHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			boolean isValidToken = jwtTokenProvider.validateToken(token);

			if (!isValidToken) {
				log.info("=====유효하지 않은 토큰=====");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setCharacterEncoding("UTF-8");
				String errorMessage = "유효하지 않은 토큰입니다";
				response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
				return;
			}

			String userId = jwtTokenProvider.getPayload(token);


			// 권한 부여
			log.info("=====권한 부여=====");
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority(Role.USER.getTitle())));


			// Detail을 넣어줌
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			log.info("[+] Token in SecurityContextHolder");
			filterChain.doFilter(request, response);

		}

	}
}

//	private boolean userHasPermission(String userId) {
//		// 여기에서 해당 사용자의 권한을 확인하는 로직 구현
//		// userId를 기반으로 데이터베이스 등에서 해당 사용자의 권한 정보를 가져와서 확인
//		// 권한이 있다면 true를 반환, 권한이 없다면 false를 반환
//		return true; // 또는 false
//	}
//}