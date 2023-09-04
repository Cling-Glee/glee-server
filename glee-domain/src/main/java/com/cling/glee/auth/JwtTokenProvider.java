package com.cling.glee.auth;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	//    private final Long accessTokenExpiredTime = 1000 * 60L * 60L * 3L; // 유효시간 3시간
	private final Long accessTokenExpiredTime = 1000 * 60L * 60L * 12L; // 유효시간 12시간 (임시 변경)
	private final Long refreshTokenExpiredTime = 1000 * 60L * 60L * 24L * 14L; // 유효시간 14일

	private final Long shortAccessTokenExpiredTime = 1000 * 60L * 5;  // 유효시간 5분


	public String createAccessToken(String payload) {
		return createJwtToken(payload, shortAccessTokenExpiredTime);
	}

	public String createRefreshToken(String payload) {
		return createJwtToken(payload, refreshTokenExpiredTime);
	}


	public String createJwtToken(String payload, long expireLength) {
		Claims claims = Jwts.claims().setSubject(payload);
		Date now = new Date();
		Date validity = new Date(now.getTime() + expireLength);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public String getPayload(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (JwtException e) {
			throw new JwtException("유효하지 않은 토큰 입니다");
		}
	}

	public void validateToken(String token) throws JwtException {
		try {
			Jws<Claims> claimsJws = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token);

			Date expiration = claimsJws.getBody().getExpiration();
			Date now = new Date();

			if (expiration.before(now)) {
				throw new ExpiredJwtException(null, null, "Token expired");
			}
		} catch (ExpiredJwtException e) {
			// 토큰이 만료된 경우 처리
			log.error("===Token expired: {}===", e.getMessage());
			throw e;
		} catch (MalformedJwtException e) {
			// 토큰 형식이 잘못된 경우 처리
			log.error("====Malformed token: {}===", e.getMessage());
			throw e;
		} catch (JwtException | IllegalArgumentException e) {
			// 기타 예외 처리
			log.error("===Invalid token: {}===", e.getMessage());
			throw e;
		}
	}


}

