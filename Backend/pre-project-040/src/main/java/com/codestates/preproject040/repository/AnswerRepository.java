package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    void deleteByIdAndUserAccount_UserId(Long answerId, String userId);
}
