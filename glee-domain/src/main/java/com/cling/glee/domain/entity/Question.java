package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "questions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@GeneratedValue(generator = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid; // 질문 UUID

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ask_user_id")
	private User askUser; // 질문한 유저

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_user_id")
	private User answerUser; // 답변한 유저

	@Column(nullable = false, length = 1000)
	private String questionContent; // 질문 내용

	@Column(nullable = false)
	private Boolean isNickNameExposed; // 닉네임 공개 여부

	@Column(nullable = false)
	private Boolean isHided; // 숨김 여부

	@Column(nullable = false)
	private Boolean isDeleted; // 삭제 여부

	@Column(nullable = false)
	private Boolean isMember; // 회원 여부

}
