package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.QuestionHashtag;
import com.codestates.preproject040.domain.UserAccount;

//((임시))Postman Post 작동 확인용으로 userAccount, questionHashtag 빼고 생성
public record QuestionRequestDto(
        String title,
        String content
) {
    public static QuestionRequestDto of(
            String title,
            String content) {
        return new QuestionRequestDto(
                title, content
        );
    }

    public Question toEntity(UserAccount userAccount){
        return Question.of(
                title,
                content,
                userAccount
        );
    }
}
