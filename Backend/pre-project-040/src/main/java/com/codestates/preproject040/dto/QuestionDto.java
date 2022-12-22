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
) {
    public static QuestionDto of(
            UserAccount userAccount,
            String title,
            QuestionHashtag questionHashtag,
            String content,
            Answer answer,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy) {

        return new QuestionDto(
                null, userAccount, title, questionHashtag, content,
                answer, null, createdBy, null, modifiedBy);
    }

    public static QuestionDto of(
            String title,
            QuestionHashtag questionHashtag,
            String content,
            String modifiedBy) {

        return new QuestionDto(
                null, null, title, questionHashtag, content,
                null, null, null, null, modifiedBy
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

    public Question toEntity() {
        return Question.of(
                title,
                content,
                userAccount
        );
    }
}
