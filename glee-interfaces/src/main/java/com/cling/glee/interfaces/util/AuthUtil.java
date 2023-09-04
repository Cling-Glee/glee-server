package com.cling.glee.interfaces.util;

import com.cling.glee.auth.CustomAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthUtil {

	public Long getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			CustomAuthenticationDetails customDetails = (CustomAuthenticationDetails) authentication.getDetails();
			return customDetails.getUserId();
		}
		return null; // 인증 정보가 없거나 CustomAuthenticationDetails를 사용하지 않는 경우
	}

	public UUID getUuid() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			CustomAuthenticationDetails customDetails = (CustomAuthenticationDetails) authentication.getDetails();
			return customDetails.getUuid();
		}
		return null; // 인증 정보가 없거나 CustomAuthenticationDetails를 사용하지 않는 경우
	}

}
