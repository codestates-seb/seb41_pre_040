package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.request.QuestionRequest;
import com.codestates.preproject040.dto.response.QuestionResponse;
import com.codestates.preproject040.dto.security.UserAccountPrincipal;
import com.codestates.preproject040.service.QuestionService;
import com.codestates.preproject040.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final UserAccountService userAccountService;

    // 검색 ex. questions/search?searchKeyword=내용&page=0
    // 실제 size=30, 일단 size=2로 작성
    @GetMapping("/search")
    public ResponseEntity getQuestions(
            @RequestParam String searchKeyword,
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal UserAccountPrincipal principal,
            HttpServletRequest httpServletRequest
    ) {
        HttpSession session = httpServletRequest.getSession(false);

        log.info("------------------------");
        log.info("Session info : {}", session);
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication());
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        log.info("------------------------");

        List<QuestionDto> questions = questionService.searchQuestions(searchKeyword, pageable);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // 작성
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionRequest request,
                                       @AuthenticationPrincipal UserAccountPrincipal principal,
                                       HttpServletRequest httpServletRequest,
                                       @CookieValue("JSESSIONID") String cookie) {
        HttpSession session = httpServletRequest.getSession(false);

        log.info("------------------------");
        log.info("Session info : {}", session);
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication());
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        log.info("cookie : {}", cookie);
        log.info("------------------------");

        /*log.info("------------------------");
        log.info("principal info : {}", principal.username());
        log.info("principal info : {}", principal.getPassword());
        log.info("------------------------");*/

        QuestionDto dto = questionService.createQuestion(request.toDto(principal.toDto()));

        return new ResponseEntity<>(QuestionResponse.from(dto), HttpStatus.CREATED);
    }

    // 1개 보기
    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive Long id,
                                      @AuthenticationPrincipal UserAccountPrincipal principal,
                                      HttpServletRequest httpServletRequest) {
        log.info("------------------------");
        log.info("Session info : {}", httpServletRequest.getSession(false));
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication());
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        log.info("------------------------");


        QuestionDto dto = questionService.findQuestion(id);
        // 일단 여기 문제는 맞음
        return new ResponseEntity<>(QuestionResponse.from(dto), HttpStatus.OK);
    }

    // (Home 화면) 목록 보기
    // 실제 size=96, 일단 size=2로 작성
    /*@GetMapping("/")
    public ResponseEntity getQuestionsHome(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<QuestionResponse> questionDtoList = questionService.findQuestions(pageable)
                .stream()
                .map(QuestionResponse::from)
                .toList();
        return new ResponseEntity<>(questionDtoList, HttpStatus.OK);
    }*/

    // (Question 화면) 목록 보기 ex. /questions?page=0
    // 실제 size=30, 일단 size=2로 작성
    @GetMapping
    public ResponseEntity getQuestions(
            @PageableDefault(size = 2, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal UserAccountPrincipal principal,
            @CookieValue("JSESSIONID") String cookie
    ) throws IOException {

        log.info("-------------------------");
        log.info("Session info : {}", request.getSession(false));
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication());
        log.info("SecurityContext info : {}", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        log.info("cookie : {}", cookie);
        log.info("-------------------------");



        List<QuestionDto> questionDtos = questionService.findQuestions(pageable);
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    // 수정
    @PatchMapping("/{questionId}")
    public ResponseEntity patchQuestion(@PathVariable("questionId") @Positive Long id,
                                        @RequestBody QuestionRequest request,
                                        @AuthenticationPrincipal UserAccountPrincipal principal) {
        QuestionDto updatedQuestion = questionService.updateQuestion(id, request.toDto(principal.toDto()));

        return new ResponseEntity<>(QuestionResponse.from(updatedQuestion), HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable("questionId") Long id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
