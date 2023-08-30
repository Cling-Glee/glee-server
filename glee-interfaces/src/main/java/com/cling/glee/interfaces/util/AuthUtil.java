package com.cling.glee.interfaces.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

	public Long getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return Long.parseLong(authentication.getPrincipal().toString());
	}

}
