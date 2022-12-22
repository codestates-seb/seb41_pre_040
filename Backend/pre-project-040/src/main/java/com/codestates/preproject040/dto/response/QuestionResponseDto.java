package com.codestates.preproject040.dto.response;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;

import java.util.List;

public record QuestionResponseDto(
        Long id,
        String title,
        String content,
        List<Hashtag> hashtags,
        String userId) {
    public static QuestionResponseDto of(
            Long id,
            String title,
            String content,
            List<Hashtag> hashtags,
            String userId
    ){
        return new QuestionResponseDto(
                id, title, content, hashtags, userId
        );
    }

    //((임시))Post 확인 위해서 hashtag에 임시로 null 지정
    public static QuestionResponseDto from(UserAccount userAccount, Question question){
        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getContent(),
                null,
                userAccount.getUserId()
        );
    }
}
