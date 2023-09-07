package com.cling.glee.interfaces.api.oauth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailJoinRequestDTO {
	private String tagName;
	private String nickName;
//	private String profileImage; 나중에 s3 연동필요
}
