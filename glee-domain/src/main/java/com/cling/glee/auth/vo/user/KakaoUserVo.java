package com.cling.glee.auth.vo.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
public class KakaoUserVo implements OAuthUserInterface {

	public Map<String, Object> attributes;

	@JsonCreator
	public KakaoUserVo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Object> getKakaoAccount(){
		return (Map<String, Object>) attributes.get("kakao_account");
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getProviderId() {
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getEmail() {
		return (String) getKakaoAccount().get("email");
	}

	@Override
	public String getName() {
		return (String) getKakaoAccount().get("nickname");
	}
}
