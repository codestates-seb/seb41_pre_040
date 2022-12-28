package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.QuestionDto;
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
            String searchKeyword,
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
) {
        List<QuestionDto> questions = questionService.searchQuestions(searchKeyword, pageable);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // 작성
    @PostMapping("/questions")
    public ResponseEntity postQuestion(@RequestBody QuestionDto requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question = requestBody.toEntity(userAccount);
        Question createdQuestion = questionService.createQuestion(question);

        return new ResponseEntity<>(QuestionResponseDto.from(userAccount, createdQuestion), HttpStatus.CREATED);
    }

    // 1개 보기
    @GetMapping("/questions/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive long id){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question = questionService.findQuestion(id);
        // 일단 여기 문제는 맞음
        return new ResponseEntity<>(QuestionDto.from(question), HttpStatus.OK);
    }

    // (Home 화면) 목록 보기
    // 실제 size=96, 일단 size=2로 작성
    @GetMapping("/")
    public ResponseEntity getQuestionsHome(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        List<QuestionDto> questionDtos = questionService.findQuestions(pageable);
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    // (Question 화면) 목록 보기 ex. /questions?page=0
    // 실제 size=30, 일단 size=2로 작성
    @GetMapping("/questions")
    public ResponseEntity getQuestions(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        List<QuestionDto> questionDtos = questionService.findQuestions(pageable);
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    // 수정
    @PatchMapping("/questions/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") @Positive long id,
                                        @RequestBody QuestionDto requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question =
                QuestionDto.of(requestBody.title(), requestBody.content1(), requestBody.content2())
                        .toEntity(userAccount);
        Question updatedQuestion = questionService.updateQuestion(id, question);

        return new ResponseEntity<>
                (QuestionResponseDto.from(userAccount, updatedQuestion), HttpStatus.OK);
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