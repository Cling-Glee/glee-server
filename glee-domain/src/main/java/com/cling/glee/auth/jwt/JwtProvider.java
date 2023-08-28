package com.cling.glee.auth.jwt;

import com.cling.glee.domain.entity.User;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@Getter
public class JwtProvider {

	@Value("${jwt.secret}")
	private String SECRET;

	@Value("${jwt.expiration}")
	private int EXPIRATION;

	@Value("${jwt.token-prefix}")
	private String TOKEN_PREFIX;

	@Value("${jwt.header-string}")
	private String HEADER_STRING;

	public String createAccessToken(User user) {
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + EXPIRATION * 1000L);

		System.out.println("now = " + now);

		System.out.println("expirationDate = " + expirationDate);

		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("userEmail", user.getEmail());
		claims.put("userOAuthProvider", user.getProviderType());


		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, SECRET)
				.compact();
	}

	// TODO
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature", e);
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token", e);
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token", e);
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token", e);
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty.", e);
		}
		return false;
	}

	public Claims getPayload(String accessToken) {
		return Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(accessToken)
				.getBody();
	}
}



