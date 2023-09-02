package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import com.cling.glee.domain.entity.enums.ProviderType;
import com.cling.glee.domain.entity.enums.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(of = {"id", "username", "age"})
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
	@Id
	@GeneratedValue
	private Long id;

	@GeneratedValue(generator = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid; // 유저 UUID

	// 닉네임
	@Column(nullable = false)
	private String nickname;

	private String age; // 범위로 들어오는 경우 있어서 String 으로 설정

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProviderType providerType; // KAKAO, TWITTER, INSTAGRAM, APPLE

	private String providerId; // 각 소셜 로그인 서비스에서 제공하는 고유 아이디

	private String email;

	// 리프레시 토큰
	private String refreshToken;

	// 프로필 사진
	private String profileImage;

	// 회원가입 완료 여부 => 필요한가?
	private boolean isJoinCompleted;

	// 회원탈퇴 여부
	@ColumnDefault("false")
	private boolean isWithdrawal;

	// 상단고정질문 (FK 세팅 안 함)
	private Long topFixedQuestionId;

	// 유저 권한
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	/* == 양방향 연관관계 == */

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // TODO 캐스케이드 옵션 공부
	private List<BlockUser> blockUsers = new ArrayList<>();

	@OneToMany(mappedBy = "askUser", cascade = CascadeType.ALL)
	private List<Question> askQuestions = new ArrayList<>(); // 특정 유저가 질문한 질문들

	@OneToMany(mappedBy = "answerUser", cascade = CascadeType.ALL)
	private List<Question> answerQuestions = new ArrayList<>(); // 특정 유저가 답변한 질문들

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Survey> surveys = new ArrayList<>();

	/* == 연관관계 편의메서드 == */
	public void addBlockUser(BlockUser blockUser) {
		blockUser.setUser(this);
		this.getBlockUsers().add(blockUser);
	}

	public void removeBlockUser(BlockUser blockUser) {
		blockUser.setUser(null);
		this.getBlockUsers().remove(blockUser);
	}

	public void askQuestion(Question question) {
		question.setAskUser(this);
		this.askQuestions.add(question);
	}

	public void answerQuestion(Question question) {
		question.setAnswerUser(this);
		this.answerQuestions.add(question);
	}

	public void removeQuestion(Question question) {
		question.setAskUser(null);
		question.setAnswerUser(null);
	}

	/* == 비즈니스 로직 == */
	public User update(String nickname, String profileImage) {
		this.nickname = nickname;
		this.profileImage = profileImage;

		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}
}
