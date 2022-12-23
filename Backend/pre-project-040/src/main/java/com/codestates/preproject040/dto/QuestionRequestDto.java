package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;

//test 작성용 questionHashtag 뺴고 진행
public record QuestionRequestDto(
        UserAccount userAccount,
        String title,
        String content
) {
    public static QuestionRequestDto of(
            UserAccount userAccount,
            String title,
            String content) {
        return new QuestionRequestDto(
                userAccount, title, content
        );
    }

    public Question toEntity(){
        return Question.of(
                title,
                content,
                userAccount
        );
    }
}

//================================================================================================================
/*
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
*/