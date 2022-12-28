package com.codestates.preproject040.dto.response;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;

import java.util.List;

public record QuestionResponseDto(
        Long id,
        String title,
        String content1,
        String content2,
        List<Hashtag> hashtags,
        String userId,
        String nickname
) {
    public static QuestionResponseDto of(
            Long id,
            String title,
            String content1,
            String content2,
            List<Hashtag> hashtags,
            String userId,
            String nickname
    ){
        return new QuestionResponseDto(
                id, title, content1, content2, hashtags, userId, nickname
        );
    }

    //((임시-Post 확인))controller에 임시사용자정보 생성, hashtag null
    public static QuestionResponseDto from(UserAccount userAccount, Question question){
        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getContent1(),
                question.getContent2(),
                null,
                userAccount.getUserId(),
                userAccount.getNickname()
        );
    }
}
