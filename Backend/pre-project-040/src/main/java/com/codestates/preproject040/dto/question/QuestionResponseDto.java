package com.codestates.preproject040.dto.question;

import java.time.LocalDateTime;

public record QuestionResponseDto(
        Long questionId,
        String nickname,
        String title,
        String content1,
        String content2,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static QuestionResponseDto of(
            Long questionId,
            String nickname,
            String title,
            String content1,
            String content2,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ){
        return new QuestionResponseDto(
                questionId, nickname, title, content1, content2, createdAt, modifiedAt
        );
    }

    public static QuestionResponseDto from(QuestionDto questionDto){
        return new QuestionResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                questionDto.createdAt(),
                questionDto.modifiedAt()
        );
    }

    public static QuestionResponseDto from(QuestionDto questionDto, List<HashtagResponseDto> hashtagDto){
        return new QuestionResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                hashtagDto,
                questionDto.createdAt(),
                questionDto.modifiedAt()
        );
    }
}
