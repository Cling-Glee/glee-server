package com.cling.glee.interfaces.api.ask.command;

import com.cling.glee.domain.service.command.AskCommandService;
import com.cling.glee.domain.service.vo.QuestionCreateVO;
import com.cling.glee.domain.service.vo.QuestionUpdateVO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionCreateCommandDTO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionDeleteCommandDTO;
import com.cling.glee.interfaces.api.ask.command.dto.QuestionUpdateCommandDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/auth/question")
@RequiredArgsConstructor
@Tag(name = "질문-회원")
public class AuthQuestionCommandController {

	private final AskCommandService askCommandService;

	// 질문 등록
	@PostMapping("/register")
	@Operation(summary = "회원 질문 등록")
	@SecurityRequirement(name = "Authorization")
	public void questionRegister(@RequestBody QuestionCreateCommandDTO questionCreateCommandDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long userId = Long.parseLong(authentication.getPrincipal().toString());

		askCommandService.registerQuestion(QuestionCreateVO.builder()
				.questionUserId(userId)
				.answerUserId(questionCreateCommandDTO.getAnswerUserId())
				.questionContent(questionCreateCommandDTO.getQuestionContent())
				.isNickNameExposed(questionCreateCommandDTO.getIsNickNameExposed())
				.isMember(true)
				.build());
	}

	// 질문 수정
	@PostMapping("/update")
	@Operation(summary = "회원 질문 수정")
	@SecurityRequirement(name = "Authorization")
	public void questionUpdate(@RequestBody QuestionUpdateCommandDTO questionUpdateCommandDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long userId = Long.parseLong(authentication.getPrincipal().toString());

		askCommandService.updateQuestion(QuestionUpdateVO.builder()
				.type(questionUpdateCommandDTO.getType())
				.questionId(questionUpdateCommandDTO.getQuestionId())
				.isActivated(questionUpdateCommandDTO.getIsActivated())
				.userId(userId)
				.build());
	}

	// 질문 삭제
	@PostMapping("/delete")
	@Operation(summary = "회원 질문 삭제")
	@SecurityRequirement(name = "Authorization")
	public void questionDelete(@RequestBody QuestionDeleteCommandDTO questionDeleteCommandDTO) {

	}

}
