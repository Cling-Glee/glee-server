package com.cling.glee.auth;

import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Getter
public class CustomAuthenticationDetails extends WebAuthenticationDetails {

	private final Long userId;
	private final UUID uuid;

	public CustomAuthenticationDetails(Long userId, UUID uuid, HttpServletRequest request) {
		super(request);
		this.userId = userId;
		this.uuid = uuid;
	}

}