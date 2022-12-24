package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.response.SingleResponseDto;
import com.codestates.preproject040.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
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
@RequiredArgsConstructor
public class HomeController {
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    //질문 보기(루트페이지)
    @GetMapping
    public ResponseEntity getQuestionsHome(){
        List<Question> questions = questionService.findQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

//    //todo : 갯수 정해서 최근 몇 개만 불러올 수 있도록 / 구글링 >>> @PageableDefault
//    //질문 보기(루트페이지)
//    @GetMapping
//    public ResponseEntity getQuestionsHome(
//            @PageableDefault(size = 96, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
//    ){
//        Page<Question> pageQuestions = questionRepository.findAll(pageable);
//        List<Question> questions =
//                pageQuestions.stream().toList();
//        List<QuestionDto> questionDtos =
//                questions.stream()
//                        .map(QuestionDto::from)
//                        .collect(Collectors.toList());
//        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
//    }
}
