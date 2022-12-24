package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.domain.constant.SearchType;
import com.codestates.preproject040.dto.QuestionDto;
//import com.codestates.preproject040.dto.QuestionRequestDto;
//import com.codestates.preproject040.dto.response.QuestionResponseDto;
import com.codestates.preproject040.dto.QuestionRequestDto;
import com.codestates.preproject040.dto.response.QuestionResponseDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import com.codestates.preproject040.response.MultiResponseDto;
import com.codestates.preproject040.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final UserRepository userRepository;

    private final QuestionRepository questionRepository;

    //((임시))질문 작성 - 임시 유저 정보 만들어서 작성
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionRequestDto requestBody,
                                       HttpServletResponse httpServletResponse) throws IOException {
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question = requestBody.toEntity(userAccount);
        Question createdQuestion = questionService.createQuestion(question);
        httpServletResponse.sendRedirect("/questions");
        return new ResponseEntity<>(QuestionResponseDto.from(userAccount, createdQuestion), HttpStatus.CREATED);
    }

    //todo: 검색
    @GetMapping("/search")
    public ResponseEntity searchQuestions(
            @RequestParam SearchType searchType,
            @RequestParam String searchKeyword,
            @PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
//        Page<QuestionResponseDto> questions =
//                questionService.searchQuestions(searchType, searchKeyword, pageable).map(QuestionResponseDto::from);
        Page<Question> questions = questionService.searchQuestions(searchType, searchKeyword, pageable)
                .map();

        return new ResponseEntity<>(questions, HttpStatus.OK);
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

    //질문 수정
    @PatchMapping("/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") @Positive long id,
                                        @RequestBody QuestionRequestDto requestBody){
        UserAccount userAccount = createUser();
        userRepository.save(userAccount);

        Question question =
                QuestionRequestDto.of(requestBody.title(), requestBody.content())
                        .toEntity(userAccount);
        Question updatedQuestion = questionService.updateQuestion(id, question);

        return new ResponseEntity<>
                (QuestionResponseDto.from(userAccount, updatedQuestion), HttpStatus.OK);
    }

    //질문 삭제
    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") long id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //((임시))유저 정보 생성
    private UserAccount createUser() {
        return UserAccount.of("임시 유저", "1234", "abc@gmail.com", "임시 닉네임");
    }

//    //((임시))퀘스천 정보 생성
//    private Question createQuestion() {
//        UserAccount userAccount = createUser();
//        userRepository.save(createUser());
//
//        return Question.of("임시 타이틀", "임시 컨텐트", userAccount);
//    }
}
