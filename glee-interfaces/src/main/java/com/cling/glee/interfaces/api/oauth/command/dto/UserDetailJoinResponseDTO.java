package com.cling.glee.interfaces.api.oauth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailJoinResponseDTO {
	private Long id;
	private String tagName;
	private String nickname;
	//	private String profileImage; 나중에 s3 연동필요
	private String email;
	private String accessToken;
	private String refreshToken;
	private boolean isJoinCompleted;
}
