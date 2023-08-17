package com.cling.glee.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "questions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ask_user_id")
	private User askUser; // 질문한 유저

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_user_id")
	private User answerUser; // 답변한 유저

	private String questionContent;
	private boolean isNickNameExposed;
	private boolean isHided;
	private boolean isDeleted;
}
