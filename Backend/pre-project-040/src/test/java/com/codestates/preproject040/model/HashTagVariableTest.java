package com.codestates.preproject040.model;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.QuestionHashtag;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HashTagVariableTest {

    private Question question;
    @Before
    public void init(){
        question=new Question();
        question.setTitle("인사말");
        question.setContent1("안녕하세여, 반갑습니다.");



    }
    @Test
    public void hasTag(){
        assertThat(question.hasTag("40기")).isEqualTo(true);
        assertThat(question.hasTag("힘드렁")).isEqualTo(false);
    }
}
