package com.cling.glee.auth.userinfo;

import com.cling.glee.domain.entity.enums.ProviderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class KakaoUserInfo implements OAuth2UserInfo {

	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Properties {
		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class KakaoAccount {
		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;

		@Data
		@JsonIgnoreProperties(ignoreUnknown = true)
		public class Profile {
			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
		}
	}

	@Override
	public String getProviderId() {
		return id.toString();
	}

	@Override
	public ProviderType getProviderType() {
		return ProviderType.kakao;
	}

	@Override
	public String getEmail() {
		return getKakao_account().getEmail();
	}


	@Override
	public String getProfileImage() {
		return getProperties().getProfile_image();
	}

	@Override
	public String getAge() {
		return getKakao_account().getProfile().getNickname();
	}

}