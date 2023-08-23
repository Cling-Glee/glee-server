package com.cling.glee.auth.vo;

import lombok.Data;

@Data
public class OAuthTokenVo {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private String refresh_token_expires_in;
	private String id_token;
}
