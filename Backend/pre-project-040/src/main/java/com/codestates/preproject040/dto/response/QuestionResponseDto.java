package com.codestates.preproject040.dto.response;

import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.answer.AnswerDto;

import java.util.List;

public record QuestionResponseDto(
        Long questionId,
        String nickname,
        String title,
        String content1,
        String content2,
        List<AnswerDto> answerDtoList
) {
    public static QuestionResponseDto of(
            Long questionId,
            String nickname,
            String title,
            String content1,
            String content2,
            List<AnswerDto> answerDtoList
    ){
        return new QuestionResponseDto(
                questionId, nickname, title, content1, content2, answerDtoList
        );
    }

    public static QuestionResponseDto from(QuestionDto questionDto){
        return new QuestionResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                questionDto.answerDtoList()
        );
    }
}
