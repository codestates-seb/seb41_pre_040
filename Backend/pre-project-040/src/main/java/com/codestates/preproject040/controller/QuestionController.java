package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.response.MultiResponseDto;
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

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //질문 작성
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto requestBody){
        Question question = requestBody.toEntity();
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(QuestionDto.from(createdQuestion), HttpStatus.CREATED);
    }

//    일단 Get 넘기고 다른것부터 진행 중
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
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive long id){
        Question question =




        return null;
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
