package com.codestates.preproject040;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.QuestionHashtag;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PreProject040ApplicationTests {

	Question question;

	@Before
	public void init(){
		question=new Question();
		QuestionHashtag questionHashtag=new QuestionHashtag();
		questionHashtag.setHashtag(new Hashtag(0l, "40기"));
		QuestionHashtag questionHashtag2=new QuestionHashtag();
		questionHashtag.setHashtag(new Hashtag(2l, "힘드러"));

		question.questionHashtags= Set.of(questionHashtag,questionHashtag2);

	}
	@Test
	public void hasTag(){
		assertThat(question.hasTag("40기")).isEqualTo(true);
		assertThat(question.hasTag("힘드렁")).isEqualTo(false);
	}

}
