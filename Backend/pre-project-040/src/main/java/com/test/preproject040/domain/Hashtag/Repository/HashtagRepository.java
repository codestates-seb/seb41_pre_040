package com.test.preproject040.domain.Hashtag.Repository;

import com.test.preproject040.domain.Hashtag.model.Hashtag;
import com.test.preproject040.domain.QuestionHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}