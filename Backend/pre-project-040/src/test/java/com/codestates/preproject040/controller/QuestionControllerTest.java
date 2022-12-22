package com.codestates.preproject040.controller;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.AnswerDto;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.repository.AnswerRepository;
import com.codestates.preproject040.service.AnswerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class QuestionControllerTest {

//    @Test
//    @DisplayName("postAnswer 테스팅 값이 이렇게 들어갈까 생각하고 테스팅 함. 후후")
//    void postAnswer() {
//        QuestionController questionController = new QuestionController(null);
//        // 퀘션, 유저는 이미 있는 정보,
//        // 유저 생성
//        UserAccount userAccount = UserAccount.of("Ssang","water","SsangSoo@Github.com", "SsangSoo");
//
//        // 퀘션 생성,
//        Question question = Question.of("title", "이거뭐에요", userAccount);
//
//        // 이게 중요함.. RequestBody로 들어가는 값..
//        AnswerDto answerDto = AnswerDto.of(question, userAccount,"이건 아무것도 아닙니다. 후후");
//
//
//        questionController.postAnswer(answerDto);
//
//        /*-----------여기까지 답변 등록 데이터 확인.------- */



//    }
}