package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;

import java.time.LocalDateTime;

public record AnswerDto(
        Long id,
        Long questionId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static AnswerDto of(Long questionId, UserAccountDto userAccountDto, String content) {
        return new AnswerDto(null, questionId, userAccountDto, content, null, null, null, null);
    }

    public static AnswerDto of(Long id, Long questionId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AnswerDto(id, questionId, userAccountDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AnswerDto from(Answer entity) {
        return new AnswerDto(
                entity.getId(),
                entity.getQuestion().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Answer toEntity(Question question, UserAccount userAccount) {
        return Answer.of(
                content,
                userAccount,
                question
        );
    }

}
