package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.QuestionHashtag;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import com.codestates.preproject040.service.QuestionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @BeforeEach
    @DisplayName("postQuestion 확인")
    void postQuestionTest() throws Exception {
        // Given

        Set<QuestionHashtag> set = new HashSet<QuestionHashtag>();
        QuestionHashtag hashtag1 = new QuestionHashtag();
        Hashtag hashtag=new Hashtag();
        hashtag.setTagName("#개발일지");
        hashtag1.setHashtag(hashtag);
        set.add(hashtag1);

        QuestionDto post =
                QuestionDto.of("title1", set,"content1", "content2");

        String postContent = gson.toJson(post);

        // When & Then
        mockMvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(postContent)
                )
                .andExpect(status().isOk());
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/questions"));
    }
}