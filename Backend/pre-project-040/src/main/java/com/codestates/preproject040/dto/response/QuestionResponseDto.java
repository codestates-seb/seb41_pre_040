package com.codestates.preproject040.dto.response;

import com.codestates.preproject040.dto.QuestionDto;

public record QuestionResponseDto(
        Long questionId,
        String nickname,
        String title,
        String content1,
        String content2
) {
    public static QuestionResponseDto of(
            Long questionId,
            String nickname,
            String title,
            String content1,
            String content2
    ){
        return new QuestionResponseDto(
                questionId, nickname, title, content1, content2
        );
    }

    public static QuestionResponseDto from(QuestionDto questionDto){
        return new QuestionResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2()
        );
    }
}
