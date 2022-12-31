package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.AnswerDto;
import com.codestates.preproject040.dto.request.AnswerRequest;
import com.codestates.preproject040.dto.response.AnswerResponse;
import com.codestates.preproject040.dto.security.UserAccountPrincipal;
import com.codestates.preproject040.service.AnswerService;
import com.codestates.preproject040.service.QuestionService;
import com.codestates.preproject040.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;


@RestController
@Slf4j
@RequestMapping("/questions")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserAccountService userAccountService;

    @PostMapping("/{questionId}/answers")   // 답변 등록
    public ResponseEntity postAnswer(@PathVariable("questionId") @Positive Long questionId,
                                     @RequestBody AnswerRequest request,
                                     @AuthenticationPrincipal UserAccountPrincipal principal) { // 나중에 유저정보를 받는다.

//        // 일단 임시유저로 대체한다.
//        UserAccount userAccount = createUser();
//        userRepository.save(userAccount);

//        System.out.println(requestBody);
//        System.out.println(questionId);
        // PostDto 가공.

        AnswerDto answer = answerService.createAnswer(request.toDto(questionId, principal.toDto()));

        // 서비스 계층으로 Dto를 주고, 서비스 안에서 Dto를 요리조리 볶는다.
        return new ResponseEntity<>(AnswerResponse.from(answer), HttpStatus.CREATED);
    }


    @PatchMapping("/{questionId}/answers/{answerId}")   // 답변 수정
    public ResponseEntity patchAnswer(@PathVariable("questionId") @Positive Long questionId,
                                      @PathVariable("answerId") @Positive Long answerId,
                                      @RequestBody AnswerRequest request,
                                      @AuthenticationPrincipal UserAccountPrincipal principal) {
        log.info("answerId = " + answerId);
        log.info("questionId = " + questionId);
        log.info("requestBody.content() = " + request.content());

        AnswerDto answerDto = answerService.updateAnswer(answerId, request.toDto(questionId, principal.toDto()));

        return new ResponseEntity(AnswerResponse.from(answerDto), HttpStatus.OK);
    }


    @DeleteMapping("/{questionId}/answers/{answerId}")   // 답변 삭제
    public ResponseEntity deleteAnswer(@PathVariable("questionId") @Positive Long questionId,
                                       @PathVariable("answerId") @Positive Long answerId,
                                       @AuthenticationPrincipal UserAccountPrincipal principal) /*인증된 유저같이 있어야 한다.*/ {

        answerService.deleteAnswer(questionId, principal.getUsername());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
