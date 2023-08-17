package com.cling.glee.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "block_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockUser {

	@Id
	@GeneratedValue
	private Long id;

	// 연관관계 주인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private User user;

	// 연관관계 주인
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private User blockUser;

	/* === 연관관계 편의메서드 === */

}
