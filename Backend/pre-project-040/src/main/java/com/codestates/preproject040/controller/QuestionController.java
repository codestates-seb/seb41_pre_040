package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.AnswerDto;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.response.MultiResponseDto;
import com.codestates.preproject040.service.AnswerService;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    //todo: 검색기능

    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    //질문 작성
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto requestBody){
        Question question = requestBody.toEntity();
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(QuestionDto.from(createdQuestion), HttpStatus.CREATED);
    }


    //질문 수정
    @PatchMapping("/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") @Positive long id,
                                        @RequestBody QuestionDto requestBody){
        Question question =
                QuestionDto.of(id, requestBody.title(), requestBody.questionHashtag(), requestBody.content())
                        .toEntity();
        Question updatedQuestion = questionService.updateQuestion(question);
        return new ResponseEntity<>(QuestionDto.from(updatedQuestion), HttpStatus.OK);
    }


    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") @Positive long id){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
