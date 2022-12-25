package com.test.preproject040.domain.Hashtag.Repository;

import com.test.preproject040.domain.Hashtag.model.Hashtag;

import com.test.preproject040.domain.Hashtag.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query("해쉬태그를 해시태그 이름 목록에서 고르세요")
    @Transactional
    List<Hashtag> findHashtags();

    @Query("SELECT hashTagFrom Hashtag hashtag hashtag.name- ?1")
    Optional<Hashtag> findHashtagByName(String name);
}
