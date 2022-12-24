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
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
        QuestionRequestDto post =
                QuestionRequestDto.of("title1", "content1");

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

    //TODO : No value at JSON path "$.data.id" 해결 필요
    /*
    @Test
    @DisplayName("patchQuestion 확인")
    void patchQuestionTest() throws Exception {
        // Given
        //post 테스트 먼저 실행 된 후 진행
        Long questionId = 1L;
        QuestionRequestDto patch =
                QuestionRequestDto.of("title2", "content2");
//        given(questionService).updateQuestion(eq(questionId), any(QuestionDto.class));
        String patchContent = gson.toJson(patch);

        // When & Then
        mockMvc.perform(
                        patch("/questions/" + questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(patchContent)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(questionId))
                .andExpect(jsonPath("$.data.title").value(patch.title()))
                .andExpect(jsonPath("$.data.content").value(patch.content()));
    }
     */

    //TODO: 나머지
}