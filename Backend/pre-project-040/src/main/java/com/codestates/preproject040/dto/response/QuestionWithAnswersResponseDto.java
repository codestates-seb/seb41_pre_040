package com.codestates.preproject040.dto.response;

import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.answer.AnswerDto;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionWithAnswersResponseDto(
        Long questionId,
        String nickname,
        String title,
        String content1,
        String content2,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<AnswerDto> answerDtoList
) {
    public static QuestionWithAnswersResponseDto of(
            Long questionId,
            String nickname,
            String title,
            String content1,
            String content2,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            List<AnswerDto> answerDtoList
    ){
        return new QuestionWithAnswersResponseDto(
                questionId, nickname, title, content1, content2, createdAt, modifiedAt, answerDtoList
        );
    }

    public static QuestionWithAnswersResponseDto from(QuestionDto questionDto){
        return new QuestionWithAnswersResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                questionDto.createdAt(),
                questionDto.modifiedAt(),
                questionDto.answerDtoList()
        );
    }

}
