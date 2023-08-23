package com.cling.glee.auth.vo.user;

import java.util.Map;

public interface OAuthUserInterface {
	Map<String, Object> getAttributes();
	String getProviderId();
	String getProvider();
	String getEmail();
	String getName();
}
