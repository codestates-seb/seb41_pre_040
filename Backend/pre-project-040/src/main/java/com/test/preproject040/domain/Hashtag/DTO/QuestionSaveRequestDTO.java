package com.test.preproject040.domain.Hashtag.DTO;

import com.test.preproject040.domain.Hashtag.model.Question;

public class QuestionSaveRequestDTO {
    private String title;
    private String url;

    public Question toEntity(){
        return Question.builder()
                .title(title)
                .url(url)
                //wrriter 뺀 상태
                .build();
    }
}
