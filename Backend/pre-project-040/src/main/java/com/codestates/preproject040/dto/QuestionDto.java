package com.codestates.preproject040.dto;

import com.codestates.preproject040.controller.QuestionController;
import com.codestates.preproject040.domain.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record QuestionDto(
        Long id,
        UserAccount userAccount,
        String title,
        Set<QuestionHashtag> questionHashtag,
        String content,
        Set<Answer> answer,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)
{
    // Post 역할 -userAccount, -questionHashtag
    public static QuestionDto of(
            String title,
            String content) {

        return new QuestionDto(
                null, null, title, null, content,
                null, null, null, null, null);
    }

    // Patch 역할 -questionHashtag
    public static QuestionDto of(
            Long id,
            String title,
            String content) {

        return new QuestionDto(
                id, null, title, null, content,
                null, null, null, null, null
        );
    }

    public static QuestionDto from(Question question) {
        return new QuestionDto(
                question.getId(),
                question.getUserAccount(),
                question.getTitle(),
                question.getQuestionHashtags(),
                question.getContent(),
                question.getAnswers(),
                question.getCreatedAt(),
                question.getCreatedBy(),
                question.getModifiedAt(),
                question.getModifiedBy()
        );
    }

    //((임시))임시 user 정보 사용
    public Question toEntity(UserAccount userAccount) {
        return Question.of(
                title,
                content,
                userAccount
        );
    }
}
