package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final QuestionRepository questionRepository;

    public HomeController(QuestionService questionService, QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    // 목록 보기 (Home 화면)
    // 데이터 개수 제한해서 최신 N 개만 나오게 작성, 실제로는 96개인데 확인할 때 보기 쉬우려고 일단 2개로 작성
    @GetMapping
    public ResponseEntity getQuestionsHome(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Question> pageQuestions = questionRepository.findAll(pageable);
        List<Question> questions = pageQuestions.stream().toList();
        List<QuestionDto> questionDtos =
                questions.stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }
}
