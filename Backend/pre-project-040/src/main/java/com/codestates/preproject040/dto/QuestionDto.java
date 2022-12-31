package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;

import java.time.LocalDateTime;

public record QuestionDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static QuestionDto of(UserAccountDto userAccountDto, String title, String content) {
        return new QuestionDto(null, userAccountDto, title, content, null, null, null, null);
    }

    public static QuestionDto of(Long id, UserAccountDto userAccountDto, String title, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new QuestionDto(id, userAccountDto, title, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static QuestionDto from(Question entity) {
        return QuestionDto.of(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Question toEntity(UserAccount userAccount) {
        return Question.of(
                title,
                content,
                userAccount
        );
    }

}
