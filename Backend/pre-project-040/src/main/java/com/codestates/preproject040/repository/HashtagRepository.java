package com.codestates.preproject040.repository;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.QuestionHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    List<Hashtag> findByTagNameContaining(QuestionHashtag tagName);
}
