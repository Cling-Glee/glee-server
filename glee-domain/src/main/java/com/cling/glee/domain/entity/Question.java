package com.cling.glee.domain.entity;

import lombok.*;
import org.hibernate.annotations.CollectionId;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ask_user_id")
	private User askUser; // 질문한 유저

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_user_id")
	private User answerUser; // 답변한 유저

	@Column(nullable = false, length = 1000)
	private String questionContent;

	@Column(nullable = false)
	private Boolean isNickNameExposed;

	@Column(nullable = false)
	private Boolean isHided;

	@Column(nullable = false)
	private Boolean isDeleted;

}
