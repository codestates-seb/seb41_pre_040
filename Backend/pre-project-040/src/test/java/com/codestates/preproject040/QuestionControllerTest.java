package com.codestates.preproject040;

import com.codestates.preproject040.controller.QuestionController;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.QuestionRequestDto;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import com.codestates.preproject040.service.QuestionService;
import com.google.gson.Gson;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.willDoNothing;
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
    @DisplayName("postQuestion 확인")
    void postQuestionTest() throws Exception {
        // Given
        QuestionRequestDto post =
                QuestionRequestDto.of("title", "content");

        String postContent = gson.toJson(post);

        // When & Then
        mockMvc.perform(
                post("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postContent)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions"));
    }
//
//    @Test
//    @DisplayName("")
//    //TODO: 나머지
}