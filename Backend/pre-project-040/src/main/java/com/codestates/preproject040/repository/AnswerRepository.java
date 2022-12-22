package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT c FROM Answer c WHERE c.id = :answerId")
    Optional<Answer> findByAnswer(long answerId);
}
