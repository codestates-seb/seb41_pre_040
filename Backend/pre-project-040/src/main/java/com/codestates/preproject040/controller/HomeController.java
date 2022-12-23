package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.response.SingleResponseDto;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class HomeController {
    private final QuestionService questionService;

    public HomeController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //todo : 갯수 정해서 최근 몇 개만 불러올 수 있도록
    //todo : 구글링 >>> @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    //질문 보기(루트페이지)
    @GetMapping
    public ResponseEntity getQuestionsHome(){
        List<Question> questions = questionService.findQuestions();

        List<QuestionDto> questionDtos =
                questions.stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }
}
