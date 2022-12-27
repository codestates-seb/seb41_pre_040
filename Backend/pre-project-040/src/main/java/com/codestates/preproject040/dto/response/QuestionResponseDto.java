package com.codestates.preproject040.dto.response;


import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.QuestionHashtag;
import com.codestates.preproject040.domain.UserAccount;


import java.lang.reflect.Array;
import java.util.ArrayList;


public record QuestionResponseDto(
        Long id,
        String title,
        String content1,
        String content2,
        ArrayList<QuestionHashtag> questionHashtags,
        String userId,
        String nickname
) {
    public static QuestionResponseDto of(
            Long id,
            String title,
            String content1,
            String content2,
            ArrayList<QuestionHashtag> questionHashtags,
            String userId,
            String nickname
    ){
        return new QuestionResponseDto(
                id, title, content1, content2, questionHashtags, userId, nickname
        );
    }

    //((임시-Post 확인))controller에 임시사용자정보 생성, hashtag null
    public static QuestionResponseDto from(UserAccount userAccount, Question question){
        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getContent1(),
                question.getContent2(),
                question.getQuestionHashtags(),
                userAccount.getUserId(),
                userAccount.getNickname()
        );
    }
}
