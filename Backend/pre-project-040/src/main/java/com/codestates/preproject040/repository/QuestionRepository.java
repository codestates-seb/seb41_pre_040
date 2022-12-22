package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    //todo : @query 사용법 / sql limit 구글링
    //todo : 홈화면 ex50개
}
