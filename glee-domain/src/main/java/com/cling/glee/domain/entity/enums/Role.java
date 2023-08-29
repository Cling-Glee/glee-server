package com.cling.glee.domain.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	GUEST("GUEST", "익명 사용자"),
	USER("USER", "일반 사용자");

	private final String key;
	private final String title;
}

