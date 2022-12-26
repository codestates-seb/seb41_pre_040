package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.QuestionHashtag;
import com.codestates.preproject040.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.Set;

public record QuestionDto(
        Long id,
        UserAccount userAccount,
        String title,
        Set<QuestionHashtag> questionHashtag,
        String content1,
        String content2,
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
            String content1,
            String content2) {

        return new QuestionDto(
                null, null, title, null, content1, content2,
                null, null, null, null, null);
    }

    // Patch 역할 -questionHashtag
    public static QuestionDto of(
            Long id,
            String title,
            String content1,
            String content2) {

        return new QuestionDto(
                id, null, title, null, content1, content2,
                null, null, null, null, null
        );
    }

    public static QuestionDto from(Question question) {
        return new QuestionDto(
                question.getId(),
                question.getUserAccount(),
                question.getTitle(),
                question.getQuestionHashtags(),
                question.getContent1(),
                question.getContent2(),
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
                content1,
                content2,
                userAccount
        );
    }
}
