package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.request.QuestionRequest;
import com.codestates.preproject040.dto.security.UserAccountPrincipal;
import com.codestates.preproject040.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionRepository questionRepository;

    @GetMapping
    public String hello() {
        return "test";
    }

    @PostMapping
    public String postNewArticle(QuestionRequest questionRequest,
                                 @AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal
    ) {
        log.info("userDetails: {}", userAccountPrincipal.getName());

        questionRepository.save(questionRequest.toEntity(userAccountPrincipal.toDto()));



        return "redirect:/";
    }


    /*@PostMapping
    public String postNewArticle(QuestionRequest questionRequest,
                                 @AuthenticationPrincipal UserAccountPrincipal userPrincipal
    ) {
        log.info("userPrincipal: {}", userPrincipal.getName());
        log.info("userPrincipal: {}", userPrincipal.getPassword());
        log.info("userPrincipal: {}", userPrincipal.email());

        if (userPrincipal == null)
            log.info("userPrincipal is NULL");

        questionRepository.save(questionRequest.toEntity(userPrincipal.toDto()));

        return "redirect:/";
    }*/
}
