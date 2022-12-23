package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
