package com.cling.glee.auth.vo.user;

import com.cling.glee.domain.entity.enums.ProviderType;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Map;

@Getter
public class KakaoUser implements OAuthUser {

	public Map<String, Object> attributes;
	public Map<String, Object> properties;

	@JsonCreator
	public KakaoUser(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Object> getKakaoAccount() {
		return (Map<String, Object>) attributes.get("kakao_account");
	}

	public Map<String, Object> getProperties() {
		return (Map<String, Object>) attributes.get("properties");
	}

	public Map<String, Object> getProfile() {
		Map<String, Object> kakaoAccount = getKakaoAccount();
		return (Map<String, Object>) kakaoAccount.get("profile");
	}

	@Override
	public String getProviderId() {
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public ProviderType getProviderType() {
		return ProviderType.KAKAO;
	}

	@Override
	public String getEmail() {
		return (String) getKakaoAccount().get("email");
	}

	@Override
	public String getNickname() {
		return (String) getProperties().get("nickname"); // 카카오 닉네임 : 카카오에서 설정한 이름
	}

	@Override
	public String getProfileImage() {
		return (String) getProfile().get("profile_image_url");


		// return (String) ((Map<String, Object>) ((Map<String, Object>) attributes.get("kakao_account")).get("profile")).get("profile_image_url");
	}
}
