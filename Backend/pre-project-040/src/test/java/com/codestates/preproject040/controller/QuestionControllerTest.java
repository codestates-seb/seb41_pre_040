package com.codestates.preproject040.controller;

import com.codestates.preproject040.dto.question.QuestionDto;
import com.codestates.preproject040.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired private QuestionService questionService;
    @Autowired private ObjectMapper objectMapper;

//    @Test
//    @DisplayName("검색 기능")
//    @Order(4)
//    void getQuestions() {
//    }

    @Test
    @DisplayName("글 3개 작성")
    @BeforeAll
    void postQuestion() throws Exception {
        QuestionDto postA = QuestionDto.of("AtestTitle", "AtestContent1", "AtestContent2");
        QuestionDto postB = QuestionDto.of("BtestTitle", "BtestContent1", "BtestContent2");
        QuestionDto postC = QuestionDto.of("CtestTitle", "CtestContent1", "CtestContent2");

        String jsonA = objectMapper.writeValueAsString(postA);
        String jsonB = objectMapper.writeValueAsString(postB);
        String jsonC = objectMapper.writeValueAsString(postC);

        // postA
        ResultActions actionsA =
                mvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonA)
                );

        actionsA.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("AtestTitle"))
                .andExpect(jsonPath("$.content1").value("AtestContent1"))
                .andExpect(jsonPath("$.content2").value("AtestContent2"));

        // postB
        ResultActions actionsB =
                mvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonB)
                );

        actionsB.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("BtestTitle"))
                .andExpect(jsonPath("$.content1").value("BtestContent1"))
                .andExpect(jsonPath("$.content2").value("BtestContent2"));

        // postC
        ResultActions actionsC =
                mvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonC)
                );

        actionsC.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("CtestTitle"))
                .andExpect(jsonPath("$.content1").value("CtestContent1"))
                .andExpect(jsonPath("$.content2").value("CtestContent2"));
    }

    @Test
    @DisplayName("1개 보기")
    @Order(1)
    void getQuestion() throws Exception {
        long questionId = 1L;

        ResultActions actions =
                mvc.perform(
                        get("/questions/" + questionId)
                );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("AtestTitle"))
                .andExpect(jsonPath("$.content1").value("AtestContent1"))
                .andExpect(jsonPath("$.content2").value("AtestContent2"));
    }

    @Test
    @DisplayName("홈 화면 전체 목록")
    @Order(2)
    void getQuestionsHome() throws Exception {
        int page = 0;
        int size = 2;
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        mvc.perform(
                        get("/")
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                // 페이지에 questionId 3, 2 나오는지 확인
                .andExpect(jsonPath("$[0].questionId").value(3))
                .andExpect(jsonPath("$[1].questionId").value(2));
    }

    @Test
    @DisplayName("퀘스천 화면 전체 목록")
    @Order(3)
    void testGetQuestions() throws Exception {
        int page = 2;
        int size = 2;
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        mvc.perform(
                        get("/")
                                .queryParam("page", String.valueOf(page))
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                // 3 페이지에 quesitonId 2, 1인지 확인 (1페이지에 6, 5 / 2페이지에 4, 3 / 1페이지에 2, 1)
                .andExpect(jsonPath("$[0].questionId").value(2))
                .andExpect(jsonPath("$[1].questionId").value(1));
    }

    @Test
    @DisplayName("글 수정")
    @Order(5)
    void patchQuestion() throws Exception {
        QuestionDto post = QuestionDto.of("BtestNewTitle", "BtestNewContent1", "BtestNewContent2");

        String json = objectMapper.writeValueAsString(post);

        long questionId = 2L;

        mvc.perform(
                        patch("/questions/" + questionId)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("BtestNewTitle"))
                .andExpect(jsonPath("$.content1").value("BtestNewContent1"))
                .andExpect(jsonPath("$.content2").value("BtestNewContent2"));
    }

    @Test
    @DisplayName("글 삭제")
    @Order(6)
    void deleteQuestion() throws Exception {
        long questionId = 3L;

        mvc.perform(
                        delete("/questions/" + questionId)
                )

                .andExpect(status().isNoContent());
    }

}