package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.dto.AnswerDto;
import com.codestates.preproject040.repository.AnswerRepository;
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
    private final AnswerRepository answerRepository;

    public AnswerController(AnswerService answerService,
                            AnswerRepository answerRepository) {
        this.answerService = answerService;
        this.answerRepository = answerRepository;
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity postAnswer(@RequestBody AnswerDto requestBody) {
        Answer answer = requestBody.toEntity();

        Answer createdAnswer = answerService.createAnswer(answer);

        return new ResponseEntity(AnswerDto.from(createdAnswer), HttpStatus.CREATED);
    }


    @PatchMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("answerId") @Positive long id,
                                      @RequestBody AnswerDto requestBody) {

        Answer answer = AnswerDto.of(id, requestBody.content()).toEntity();
        Answer updatedAnswer = answerService.updateAnswer(answer);

        return new ResponseEntity(AnswerDto.from(updatedAnswer), HttpStatus.OK);
    }


    @DeleteMapping("/{quetionId}/answers/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") @Positive long id) {

        answerRepository.deleteById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
