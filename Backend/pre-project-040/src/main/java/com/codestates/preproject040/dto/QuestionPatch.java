package com.codestates.preproject040.dto;

public record QuestionPatch(
        String title,
        String content1,
        String content2
) {
    public static QuestionPatch of(
            String title,
            String content1,
            String content2) {

        return new QuestionPatch(
                title, content1, content2
        );
    }

    public QuestionDto toDto(UserAccountDto userAccountDto) {
        return QuestionDto.of(
                title,
                content1,
                content2,
                userAccountDto
        );
    }
}