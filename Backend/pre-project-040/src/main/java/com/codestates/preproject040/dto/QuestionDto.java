package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.*;

import java.time.LocalDateTime;

public record QuestionDto(
            Long id,
            UserAccount userAccount,
            String title,
            QuestionHashtag questionHashtag,
            String content,
            Answer answer,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
)
{
    // Post 역할
    public static QuestionDto of(
            UserAccount userAccount,
            String title,
            QuestionHashtag questionHashtag,
            String content) {

        return new QuestionDto(
                null, userAccount, title, questionHashtag, content,
                null, null, null, null, null);
    }

    // Patch 역할
    public static QuestionDto of(
            Long id,
            String title,
            QuestionHashtag questionHashtag,
            String content) {

        return new QuestionDto(
                id, null, title, questionHashtag, content,
                null, null, null, null, null
        );
    }

    public static QuestionDto from(Question question) {
        return new QuestionDto(
                question.getId(),
                question.getUserAccount(),
                question.getTitle(),
                (QuestionHashtag) question.getQuestionHashtags(),
                question.getContent(),
                (Answer) question.getAnswers(),
                question.getCreatedAt(),
                question.getCreatedBy(),
                question.getModifiedAt(),
                question.getModifiedBy()
        );
    }

//    //todo: 삭제해도 되나..?
//    //((임시))post 테스트용
//    public Question toEntity(UserAccount userAccount) {
//        return Question.of(
//                title,
//                content,
//                userAccount
//        );
//    }

    public Question toEntity() {
        return Question.of(
                title,
                content,
                userAccount
        );
    }

//    답변이 포함된 게시글 객체 만들어야함.
}