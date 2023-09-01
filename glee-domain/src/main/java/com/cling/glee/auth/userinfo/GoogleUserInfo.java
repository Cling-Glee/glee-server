package com.cling.glee.auth.userinfo;

import com.cling.glee.domain.entity.enums.ProviderType;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo {

	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public String getProviderId() {
		return null;
	}

	@Override
	public ProviderType getProviderType() {
		return null;
	}

	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public String getNickname() {
		return null;
	}

	@Override
	public String getProfileImage() {
		return null;
	}

	@Override
	public String getAge() {
		return null;
	}


}
