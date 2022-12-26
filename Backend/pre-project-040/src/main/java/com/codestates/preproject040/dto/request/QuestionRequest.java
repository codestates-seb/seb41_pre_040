package com.codestates.preproject040.dto.request;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.UserAccountDto;

public record QuestionRequest(
        String title,
        String content
) {
    public Question toEntity(UserAccountDto dto) {
        return Question.of(
                title,
                content,
                dto.toEntity()
        );
    }
}
