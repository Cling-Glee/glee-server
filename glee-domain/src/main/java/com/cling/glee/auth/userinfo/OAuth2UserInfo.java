package com.cling.glee.auth.userinfo;

import com.cling.glee.domain.entity.enums.ProviderType;

import java.util.Map;

public interface OAuth2UserInfo {
	Map<String, Object> getAttributes();

	String getProviderId();

	ProviderType getProviderType();

	String getEmail();

	String getNickname();

	String getProfileImage();
}
