package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    //findBy(컬럼이름)Containing = 컬럼에서 키워드 포함된 것 찾기
    Page<Question> findByTitleContaining(String title, Pageable pageable);
    Page<Question> findByContent1Containing(String content1, Pageable pageable);

    Page<Question> findByContent2Containing(String content2, Pageable pageable);
}