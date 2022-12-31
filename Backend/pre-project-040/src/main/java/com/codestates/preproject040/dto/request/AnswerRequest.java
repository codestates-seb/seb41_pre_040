package com.codestates.preproject040.dto.request;

import com.codestates.preproject040.dto.AnswerDto;
import com.codestates.preproject040.dto.UserAccountDto;

public record AnswerRequest(
        String content
) {
    public static AnswerRequest of(String content) {
        return new AnswerRequest(content);
    }

    public AnswerDto toDto(Long questionId, UserAccountDto dto) {
        return AnswerDto.of(
                questionId,
                dto,
                content
        );
    }

}
