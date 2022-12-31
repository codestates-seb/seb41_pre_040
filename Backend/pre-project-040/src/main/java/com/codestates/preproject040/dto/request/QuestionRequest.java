package com.codestates.preproject040.dto.request;

import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.UserAccountDto;

public record QuestionRequest(
        String title,
        String content
) {
    public static QuestionRequest of(String title, String content) {
        return new QuestionRequest(title, content);
    }

    public QuestionDto toDto(UserAccountDto dto) {
        return QuestionDto.of(
                dto,
                title,
                content
        );
    }

}
