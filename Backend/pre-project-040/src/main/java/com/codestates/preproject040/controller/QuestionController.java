package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.QuestionDto;
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

    // 작성
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question = requestBody.toEntity(userAccount);
        Question createdQuestion = questionService.createQuestion(question);

        return new ResponseEntity<>(QuestionResponseDto.from(userAccount, createdQuestion), HttpStatus.CREATED);
    }

    // TODO : 검색 기능 구현

    // 목록 보기 (Question 화면)
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

    // 1개 보기
    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive long id){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question = questionService.findQuestion(id);
        return new ResponseEntity<>(QuestionDto.from(question), HttpStatus.OK);
    }

    // 수정
    @PatchMapping("/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") @Positive long id,
                                        @RequestBody QuestionDto requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question =
                QuestionDto.of(requestBody.title(), requestBody.content())
                        .toEntity(userAccount);
        Question updatedQuestion = questionService.updateQuestion(id, question);

        return new ResponseEntity<>
                (QuestionResponseDto.from(userAccount, updatedQuestion), HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") long id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 임시 유저 정보 생성 - 추후 삭제 예정
    private UserAccount createUser() {
        return UserAccount.of("임시 유저", "1234", "abc@gmail.com", "임시 닉네임");
    }
}