package com.cling.glee.auth.vo.user;

import com.cling.glee.domain.entity.enums.ProviderType;

import java.util.Map;

public interface OAuthUser {
	Map<String, Object> getAttributes();
	String getProviderId();
	ProviderType getProviderType();
	String getEmail();
	String getNickname();
	String getProfileImage();
}
