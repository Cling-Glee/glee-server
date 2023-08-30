package com.cling.glee.interfaces.api.ask.command.dto;

import lombok.Data;

@Data
public class QuestionUpdateCommandDTO {
	private String type;
	/**
	 * HIDE : 숨기기
	 * FIXED : 상단 고정
	 **/
	private Long questionId;
	private Boolean isActivated;
}
