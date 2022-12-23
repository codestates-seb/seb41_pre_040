package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.dto.AnswerDto;
import com.codestates.preproject040.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;


@RestController
@RequestMapping("/questions/{questionId}/anwsers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerDto requestBody) {
        Answer postAnswer = requestBody.toEntity(); // 받은 Dto클래스를 Enttity로 변환

        Answer createdAnswer = answerService.createAnswer(postAnswer);  // Entity로 변환 된 답변을 서비스를 이용해서,

        return new ResponseEntity<>(AnswerDto.from(createdAnswer), HttpStatus.CREATED);
    }


    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @RequestBody AnswerDto requestBody) {
        Answer patchAnswer = AnswerDto.of(answerId, requestBody.content()).toEntity();

        Answer updatedAnswer = answerService.updateAnswer(patchAnswer);

    }


}
