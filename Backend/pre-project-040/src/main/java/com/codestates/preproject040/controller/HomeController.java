package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.response.SingleResponseDto;
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

    public HomeController(QuestionService questionService,
                          QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    //todo : 갯수 정해서 최근 몇 개만 불러올 수 있도록 / 구글링 >>> @PageableDefault
    //질문 보기(루트페이지)
    @GetMapping
    public ResponseEntity getQuestionsHome(
            //실제로는 96개있는데 보기 편하려고 일단 size 2로 작성
            @PageableDefault(size = 2, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable
            ){
        Page<Question> pageQuestions = questionRepository.findAll(pageable);
        List<Question> questions =
                pageQuestions.stream().toList();
        List<QuestionDto> questionDtos =
                questions.stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }
}
