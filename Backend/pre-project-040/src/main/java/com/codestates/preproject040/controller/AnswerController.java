package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.dto.AnswerDto;
import com.codestates.preproject040.repository.AnswerRepository;
import com.codestates.preproject040.service.AnswerService;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;


@RestController
@RequestMapping("/questions/")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;


    public AnswerController(AnswerService answerService,
                            QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity postAnswer(@RequestBody AnswerDto requestBody) {
        questionService.findVerifiedQuestion(requestBody.question().getId());

        Answer answer = requestBody.toEntity();

        Answer createdAnswer = answerService.createAnswer(answer);

        return new ResponseEntity(AnswerDto.from(createdAnswer), HttpStatus.CREATED);
    }


    @PatchMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("answerId") @Positive long id,
                                      @RequestBody AnswerDto requestBody) {
        questionService.findVerifiedQuestion(requestBody.question().getId());
        Answer answer = AnswerDto.of(id, requestBody.content()).toEntity();
        Answer updatedAnswer = answerService.updateAnswer(answer);

        return new ResponseEntity(AnswerDto.from(updatedAnswer), HttpStatus.OK);
    }


    @DeleteMapping("/{quetionId}/answers/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") @Positive long id,
                                       @RequestBody AnswerDto requestBody) {
        questionService.findVerifiedQuestion(requestBody.question().getId());

        answerService.deleteAnswer(requestBody.id());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
