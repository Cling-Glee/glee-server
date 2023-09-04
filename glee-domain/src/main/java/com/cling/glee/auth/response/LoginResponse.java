package com.cling.glee.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
//@NoArgsConstructor
@Builder
public class LoginResponse {
	private Long id;
	private String nickname;
	private String email;
	private String accessToken;
	private String refreshToken;
	private Boolean isJoinCompleted;
}

