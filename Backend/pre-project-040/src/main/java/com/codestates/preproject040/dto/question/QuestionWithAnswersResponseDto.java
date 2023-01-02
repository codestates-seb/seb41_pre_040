package com.codestates.preproject040.dto.question;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import com.codestates.preproject040.dto.Hashtag.HashtagResponseDto;
import com.codestates.preproject040.dto.Hashtag.QuestionHashtagDto;
import com.codestates.preproject040.dto.answer.AnswerDto;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionWithAnswersResponseDto(
        Long questionId,
        String nickname,
        String title,
        String content1,
        String content2,
        List<HashtagResponseDto> hashtags,
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
            List<HashtagResponseDto> hashtags,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            List<AnswerDto> answers
    ){
        return new QuestionWithAnswersResponseDto(
                questionId, nickname, title, content1, content2, hashtags, createdAt, modifiedAt, answers
        );
    }

    public static QuestionWithAnswersResponseDto from(QuestionDto questionDto){
        return new QuestionWithAnswersResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                null,
                questionDto.createdAt(),
                questionDto.modifiedAt(),
                questionDto.answers()
        );
    }

    public static QuestionWithAnswersResponseDto from(QuestionDto questionDto, List<HashtagResponseDto> hashtagDto){
        return new QuestionWithAnswersResponseDto(
                questionDto.questionId(),
                questionDto.userAccountDto().nickname(),
                questionDto.title(),
                questionDto.content1(),
                questionDto.content2(),
                hashtagDto,
                questionDto.createdAt(),
                questionDto.modifiedAt(),
                questionDto.answers()
        );
    }

}
