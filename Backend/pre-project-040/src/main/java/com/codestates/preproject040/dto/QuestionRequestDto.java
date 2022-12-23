package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;

//test 작성용 questionHashtag 뺴고 진행
public record QuestionRequestDto(
        Long id,
        String title,
        String content
) {
    public static QuestionRequestDto of(
            String title,
            String content) {
        return new QuestionRequestDto(
                null, title, content
        );
    }

    public static QuestionRequestDto of(
            Long id,
            String title,
            String content) {
        return new QuestionRequestDto(
                id, title, content
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