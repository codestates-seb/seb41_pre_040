package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.response.QuestionResponseDto;
import com.codestates.preproject040.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired private QuestionService questionService;
    @Autowired private ObjectMapper objectMapper;

//    @Test
//    @DisplayName("검색 기능")
//    void getQuestions() {
//    }

//    //TODO : failed to lazily initialize a collection of role: com.codestates.preproject040.domain.Question.answers, could not initialize proxy - no Session
//    @Test
//    @DisplayName("글 작성")
////    @RepeatedTest(3)
//    @BeforeEach
//    @Order(1)
//    void postQuestion() throws Exception {
//        QuestionDto post = QuestionDto.of("testTitle", "testContent1", "testContent2");
//
//        String json = objectMapper.writeValueAsString(post);
//
//        ResultActions actions =
//            mvc.perform(
//                    post("/questions")
//                            .accept(MediaType.APPLICATION_JSON_VALUE)
//                            .contentType(MediaType.APPLICATION_JSON_VALUE)
//                            .content(json)
//            );
//
//        QuestionResponseDto question = questionService.findQuestion(1L);
//
//        actions.andExpect(status().isCreated())
//                .andExpect(jsonPath("$.title").value(question.title()))
//                .andExpect(jsonPath("$.content1").value(question.content1()))
//                .andExpect(jsonPath("$.content2").value(question.content2()));
//    }

//    @Test
//    @DisplayName("1개 보기")
//    @Order(2)
//    void getQuestion() throws Exception {
//        long questionId = 1L;
//
//        ResultActions actions =
//                mvc.perform(
//                        get("/questions/" + questionId)
//                );
//
//        Question question = questionService.findQuestion(questionId);
//
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value(question.getTitle()))
//                .andExpect(jsonPath("$.content1").value(question.getContent1()))
//                .andExpect(jsonPath("$.content2").value(question.getContent2()));
//    }
//
////    @Test
////    @DisplayName("홈 화면 전체 목록")
////    void getQuestionsHome() {
////    }
//
////    TODO : 코드 수정
////    @Test
////    @DisplayName("퀘스천 화면 전체 목록")
////    @Order(4)
////    void testGetQuestions() throws Exception {
////        int page = 0;
////        int size = 2;
////        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
////
////        ResultActions actions =
////                mvc.perform(
////                        get("/questions")
////                                .queryParam("page", String.valueOf(page))
////                                .accept(MediaType.APPLICATION_JSON)
////                );
////
////        MvcResult result = actions
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$").isArray())
////                .andReturn();
////
////        List list1 = JsonPath.parse(result.getResponse().getContentAsString()).read("$[0]");
//////        List list2 = JsonPath.parse(result.getResponse().getContentAsString()).read("$[1]");
////
////        assertThat(list1.size(), is(2));
////    }
//
//    @Test
//    @DisplayName("글 수정")
//    @Order(3)
//    void patchQuestion() throws Exception {
//        QuestionDto post = QuestionDto.of("testNewTitle", "testNewContent1", "testNewContent2");
//
//        String json = objectMapper.writeValueAsString(post);
//
//        long questionId = 2L;
//
//        ResultActions actions =
//                mvc.perform(
//                        patch("/questions/" + questionId)
//                                .accept(MediaType.APPLICATION_JSON_VALUE)
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .content(json)
//                );
//
//        Question question = questionService.findQuestion(questionId);
//
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value(question.getTitle()))
//                .andExpect(jsonPath("$.content1").value(question.getContent1()))
//                .andExpect(jsonPath("$.content2").value(question.getContent2()));
//    }
//
//    @Test
//    @DisplayName("글 삭제")
//    @Order(5)
//    void deleteQuestion() throws Exception {
//        long questionId = 1L;
//
//        ResultActions actions =
//                mvc.perform(
//                        delete("/questions/" + questionId)
//                );
//
//        actions.andExpect(status().isNoContent());
//    }

}