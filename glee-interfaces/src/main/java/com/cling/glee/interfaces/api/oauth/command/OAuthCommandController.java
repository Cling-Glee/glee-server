package com.cling.glee.interfaces.api.oauth.command;

import com.cling.glee.auth.OAuth2Service;
import com.cling.glee.auth.response.LoginResponse;
import com.cling.glee.domain.service.dto.UserDetailJoinServiceRequestDTO;
import com.cling.glee.domain.service.dto.UserDetailJoinServiceResponseDTO;
import com.cling.glee.interfaces.api.oauth.command.dto.UserDetailJoinRequestDTO;
import com.cling.glee.interfaces.api.oauth.command.dto.UserDetailJoinResponseDTO;
import com.cling.glee.interfaces.util.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "인증", description = "OAuth2")
public class OAuthCommandController {

	private final OAuth2Service oAuth2Service;
	private final AuthUtil authUtil;
	private final ModelMapper modelMapper;

	@Operation(summary = "OAuth2 로그인 후 access token, refresh token 발급")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공"),
	})
	@GetMapping("/login/{provider}")
	public ResponseEntity<LoginResponse> login(
			@Parameter(description = "oauth 프로바이더 (ex. kakao)") @PathVariable String provider, @Parameter(description = "프로바이더 인증 코드") @RequestParam String code) {
		LoginResponse loginResponse = oAuth2Service.login(provider, code);
		return ResponseEntity.ok().body(loginResponse);
	}

	@Operation(summary = "로그아웃 (연결끊기)", description = "헤더에 access token 필요")
	@GetMapping("auth/logout/{provider}")
	@SecurityRequirement(name = "Authorization") // 인증 필요한 엔드포인트에 설정
	public ResponseEntity<String> logout(
			@Parameter(description = "oauth 프로바이더 (ex. kakao)") @PathVariable String provider
	) {
		try {
			Long userId = authUtil.getUserId();
			oAuth2Service.logout(provider, userId);
			return ResponseEntity.ok().body("logout 성공");
		} catch (Exception e) {
			log.error("logout error", e);
			return ResponseEntity.badRequest().body("logout 도중 에러가 발생했습니다");
		}

	}

	@PostMapping("/auth/join")
	public ResponseEntity<UserDetailJoinResponseDTO> detailJoin(@RequestBody UserDetailJoinRequestDTO userDetailJoinRequestDTO) {

		// userDetailJoinRequestDTO 를 userDetailJoinServiceRequestDTO 로 바꿔야함

		UserDetailJoinServiceRequestDTO userDetailJoinServiceRequestDTO = modelMapper.map(userDetailJoinRequestDTO, UserDetailJoinServiceRequestDTO.class);

		userDetailJoinServiceRequestDTO.setId(authUtil.getUserId());

		UserDetailJoinServiceResponseDTO userDetailJoinServiceResponseDTO = oAuth2Service.detailJoin(userDetailJoinServiceRequestDTO);

		UserDetailJoinResponseDTO response = modelMapper.map(userDetailJoinServiceResponseDTO, UserDetailJoinResponseDTO.class);

		return ResponseEntity.ok().body(response);
	}


}
