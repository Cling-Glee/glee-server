package com.cling.glee.domain.entity;

import com.cling.glee.domain.entity.base.BaseTimeEntity;
import com.cling.glee.domain.entity.enums.ProviderType;
import lombok.*;

import javax.persistence.*;
import java.util.Iterator;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA entity 의 기본생성자(protected)
@ToString(of = {"id", "username", "age"})
@Table(name = "users")
@Builder
@AllArgsConstructor
public class User extends BaseTimeEntity {
	@Id
	@GeneratedValue
	private Long id;

	private String username;
	private int age;

	@Enumerated(EnumType.STRING)
	private ProviderType providerType; // KAKAO, TWITTER, INSTAGRAM, APPLE

	private String providerId; // 각 소셜 로그인 서비스에서 제공하는 고유 아이디

	private String email;

}
