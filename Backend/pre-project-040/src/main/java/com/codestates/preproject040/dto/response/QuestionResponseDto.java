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

    //((임시-Post 확인))test코드, hashtag null
    public static QuestionResponseDto from(Question question){
        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getContent(),
                null,
                question.getUserAccount().getUserId()
        );
    }

//================================================================================================================
//    //((임시-Post 확인))controller에 임시사용자정보 생성, hashtag null
//    //매개변수로 UserAccount, Question
//    public static QuestionResponseDto from(UserAccount userAccount, Question question){
//        return new QuestionResponseDto(
//                question.getId(),
//                question.getTitle(),
//                question.getContent(),
//                null,
//                userAccount.getUserId()
//        );
//    }
}
