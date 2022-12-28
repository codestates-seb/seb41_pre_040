package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.QuestionPatch;
import com.codestates.preproject040.dto.QuestionPost;
import com.codestates.preproject040.dto.UserAccountDto;
import com.codestates.preproject040.dto.response.QuestionResponseDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import com.codestates.preproject040.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final UserRepository userRepository;

    public QuestionController(QuestionService questionService,
                              UserRepository userRepository) {
        this.questionService = questionService;
        this.userRepository = userRepository;
    }

    // 검색 ex. questions/search?searchKeyword=내용&page=0
    // 실제 size=30, 일단 size=2로 작성
    @GetMapping("/questions/search")
    public ResponseEntity getQuestions(
        //TODO : answer값 없는 responseDto로 리팩토링 필요
            String searchKeyword,
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<QuestionResponseDto> questionList = questionService.searchQuestions(searchKeyword, pageable);
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    // 작성
    @PostMapping("/questions")
    public ResponseEntity postQuestion(@RequestBody QuestionPost requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        //변수로 userAccount를 userAccountDto.from으로 묶어서 Dto로 만들고
        //QuestionPostDto에서 QuestionDto로 보낼 때 매개변수로 넣어줌
        //QuestionDto 객체를 questionService의 createQuestion으로 넣어주고
        QuestionDto question = requestBody.toDto(UserAccountDto.from(userAccount));
        QuestionResponseDto createdQuestion = questionService.createQuestion(question);

        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    // 1개 보기
    @GetMapping("/questions/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive long id){
        QuestionResponseDto question = questionService.findQuestion(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    // (Home 화면) 목록 보기
    // 실제 size=96, 일단 size=2로 작성
    @GetMapping("/")
    public ResponseEntity getQuestionsHome(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        //TODO : answer값 없는 responseDto로 리팩토링 필요
        List<QuestionResponseDto> questionList = questionService.findQuestions(pageable);
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    // (Question 화면) 목록 보기 ex. /questions?page=0
    // 실제 size=30, 일단 size=2로 작성
    @GetMapping("/questions")
    public ResponseEntity getQuestions(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        //TODO : answer값 없는 responseDto로 리팩토링 필요
        List<QuestionResponseDto> questionList = questionService.findQuestions(pageable);
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    // 수정
    //PatchDto로 body 받아오고, responseDto형식으로 보내기
    @PatchMapping("/questions/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") @Positive long id,
                                        @RequestBody QuestionPatch requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        QuestionDto question = requestBody.toDto(UserAccountDto.from(userAccount));
        QuestionResponseDto updatedQuestion = questionService.updateQuestion(id, question);

        return new ResponseEntity<>
                (updatedQuestion, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") long id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 임시 유저 정보 생성 - 추후 삭제 예정
    private UserAccount createUser() {
        return UserAccount.of("tmpUser", "1234", "abc@gmail.com", "임시 닉네임");
    }
}