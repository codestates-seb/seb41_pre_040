package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.response.MultiResponseDto;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    //todo: 검색..
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto requestBody){
        Question question = requestBody.toEntity();
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(QuestionDto.from(createdQuestion), HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity getQuestions(@RequestParam int page,
//                                       @RequestParam int size) {
//        Page<Question> pageQuestions = questionService.findQuestions(page-1, size);
//        List<Question> questions = pageQuestions.getContent();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(QuestionDto.from(questions), pageQuestions), HttpStatus.OK));
//    }

    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") long id){
        return null;
    }

    @PatchMapping("/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") long id,
                                        @RequestBody QuestionRequestDto requestBody){
        return null;
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") long id){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
