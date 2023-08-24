package com.cling.glee.auth.jwt;

import com.cling.glee.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

	@Value("${jwt.secret}")
	private String SECRET;

	@Value("${jwt.expiration}")
	private int EXPIRATION;

	@Value("${jwt.token-prefix}")
	private String TOKEN_PREFIX;

	@Value("${jwt.header-string}")
	private String HEADER_STRING;

	public String createJwtToken(User user) {
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + EXPIRATION);

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


}
