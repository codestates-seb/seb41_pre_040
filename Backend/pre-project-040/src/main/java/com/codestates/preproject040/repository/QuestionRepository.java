package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByTitleContaining(String title, Pageable pageable);
    Page<Question> findByContentContaining(String content1, Pageable pageable);

}
