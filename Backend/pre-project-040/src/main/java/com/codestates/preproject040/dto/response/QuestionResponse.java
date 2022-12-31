package com.codestates.preproject040.dto.response;

import com.codestates.preproject040.dto.QuestionDto;

import java.time.LocalDateTime;

public record QuestionResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickname
) {
    public static QuestionResponse of(Long id, String title, String content, LocalDateTime createdAt, String email, String nickname) {
        return new QuestionResponse(id, title, content, createdAt, email, nickname);
    }

    public static QuestionResponse from(QuestionDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank())
            nickname = dto.userAccountDto().userId();

        return new QuestionResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.createdAt(),
                dto.userAccountDto().email(),
                nickname
        );
    }

}
