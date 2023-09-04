package com.cling.glee.auth.userinfo;

import com.cling.glee.domain.entity.enums.ProviderType;

public interface OAuth2UserInfo {

	String getProviderId();

	ProviderType getProviderType();

	String getEmail();

	String getProfileImage();

	String getAge();
}
