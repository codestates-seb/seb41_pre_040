package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.QuestionRequestDto;
import com.codestates.preproject040.dto.response.QuestionResponseDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import com.codestates.preproject040.response.MultiResponseDto;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    //todo: 검색기능

    private final QuestionService questionService;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionService questionService,
                              UserRepository userRepository,
                              QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    //질문 작성
//    @PostMapping
//    public ResponseEntity postQuestion(@RequestBody QuestionDto requestBody){
//        UserAccount userAccount = createUser();
//        Question question = requestBody.toEntity(userAccount);
//        Question createdQuestion = questionService.createQuestion(question);
//        return new ResponseEntity<>(QuestionDto.from(createdQuestion), HttpStatus.CREATED);
//    }

//  //질문 올리기
//    @PostMapping
//    public ResponseEntity postQuestion(@RequestBody QuestionDto requestBody){
//        Question question = requestBody.toEntity();
//        Question createdQuestion = questionService.createQuestion(question);
//        return new ResponseEntity<>(QuestionDto.from(createdQuestion), HttpStatus.CREATED);
//    }

    //((임시))임시 유저 정보 만들어서 작성
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionRequestDto requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);
        Question question = requestBody.toEntity(userAccount);
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(QuestionResponseDto.from(userAccount, createdQuestion), HttpStatus.CREATED);
    }

    //질문 보기(Question 페이지)
    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                       @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto> questionDtos =
                questions.stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(
                new MultiResponseDto<>(questionDtos, pageQuestions), HttpStatus.OK);
    }

    //질문(1개) 보기
    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive long id){
        Question foundQuestion = questionService.findQuestion(id);
        return new ResponseEntity<>(QuestionDto.from(foundQuestion), HttpStatus.OK);
    }

    //todo: post 임시용 수정 필요> 일단 post 확인할라고 막아둠
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

    //질문 삭제
    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") long id){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //((임시))유저 정보 생성
    private UserAccount createUser() {
        return UserAccount.of("임시 유저", "1234", "abc@gmail.com", "임시 닉네임");
    }
}