package com.cling.glee.domain.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetailJoinVO {
	private String tagName;
	private String nickname;
//	private String profileImage; 나중에 s3 연동필요
}
