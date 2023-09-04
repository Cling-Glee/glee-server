package com.cling.glee.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailJoinServiceRequestDTO {
	private Long id;
	private String tagName;
	private String nickName;
//	private String profileImage; 나중에 s3 연동필요
}
