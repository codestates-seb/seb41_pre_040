package com.codestates.preproject040.controller;


import com.codestates.preproject040.dto.answer.AnswerDto;
import com.codestates.preproject040.dto.answer.AnswerPatch;
import com.codestates.preproject040.dto.answer.AnswerPost;
import com.codestates.preproject040.dto.answer.AnswerResponse;
import com.codestates.preproject040.dto.security.UserAccountPrincipal;
import com.codestates.preproject040.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.answer.AnswerDto;
import com.codestates.preproject040.dto.answer.AnswerPatch;
import com.codestates.preproject040.dto.answer.AnswerPost;
import com.codestates.preproject040.repository.UserRepository;
import com.codestates.preproject040.service.AnswerService;
import com.codestates.preproject040.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;


@RestController
@Slf4j
@RequestMapping("/questions")

@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/{questionId}/answers")   // 답변 등록
    public ResponseEntity postAnswer(@PathVariable("questionId") @Positive Long questionId,
                                     @RequestBody AnswerPost requestBody,
                                     @AuthenticationPrincipal UserAccountPrincipal principal) {

        AnswerDto answer = answerService.createAnswer(requestBody.toDto(principal.toDto()));

        // 서비스 계층으로 Dto를 주고, 서비스 안에서 Dto를 요리조리 볶는다.
        return new ResponseEntity<>(AnswerResponse.from(answer), HttpStatus.CREATED);

public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserRepository userRepository;

    public AnswerController(AnswerService answerService,
                            QuestionService questionService,
                            UserRepository userRepository) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{questionId}/answers")   // 답변 등록
    public ResponseEntity postAnswer(@PathVariable("questionId") @Positive Long questionId
                                    ,@RequestBody AnswerPost requestBody) { // 나중에 유저정보를 받는다.

//        // 일단 임시유저로 대체한다.
//        UserAccount userAccount = createUser();
//        userRepository.save(userAccount);

//        System.out.println(requestBody);
//        System.out.println(questionId);
        // PostDto 가공.
        AnswerPost answerPost = AnswerPost.of(questionId, requestBody.content());

        // 서비스 계층으로 Dto를 주고, 서비스 안에서 Dto를 요리조리 볶는다.
        return new ResponseEntity<>(AnswerDto.from(answerService.createAnswer(answerPost)), HttpStatus.CREATED);
>>>>>>> origin/QnA
    }


    @PatchMapping("/{questionId}/answers/{answerId}")   // 답변 수정
    public ResponseEntity patchAnswer(@PathVariable("questionId") @Positive Long questionId,
<<<<<<< HEAD
                                      @PathVariable("answerId") @Positive Long answerId,
                                      @RequestBody AnswerPatch requestBody,
                                      @AuthenticationPrincipal UserAccountPrincipal principal) {
=======
                                    @PathVariable("answerId") @Positive Long answerId,
                                      @RequestBody AnswerPatch requestBody) {
>>>>>>> origin/QnA
        System.out.println("answerId = " + answerId);
        System.out.println("questionId = " + questionId);
        System.out.println("requestBody.content() = " + requestBody.content());

<<<<<<< HEAD
        AnswerDto answerDto = answerService.updateAnswer(answerId, requestBody.toDto(principal.toDto()));

        return new ResponseEntity(AnswerResponse.from(answerDto), HttpStatus.OK);
=======
        AnswerPatch answerPatch = AnswerPatch.of(answerId, questionId, requestBody.content());

        return new ResponseEntity<>(AnswerDto.from(answerService.updateAnswer(answerPatch)), HttpStatus.OK);
>>>>>>> origin/QnA
    }


    @DeleteMapping("/{questionId}/answers/{answerId}")   // 답변 삭제
    public ResponseEntity deleteAnswer(@PathVariable("questionId") @Positive Long questionId,
<<<<<<< HEAD
                                       @PathVariable("answerId") @Positive Long answerId,
                                       @AuthenticationPrincipal UserAccountPrincipal principal) {

        answerService.deleteAnswer(questionId, principal.getUsername());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

=======
                                       @PathVariable("answerId") @Positive Long answerId) /*인증된 유저같이 있어야 한다.*/ {

        answerService.deleteAnswer(questionId, answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


>>>>>>> origin/QnA
}
