package com.codestates.preproject040.dto;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;

import java.time.LocalDateTime;

public record AnswerDto(
        Long id,
        Question question,
        UserAccount userAccount,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)
{   // 등록에 대한 Post 역할을 하는 of
    public static AnswerDto of(Question question, UserAccount userAccount, String content) {
        return new AnswerDto(null, question, userAccount, content, null, null, null, null);
    }

    // patch를 위한 of
    // 질문, 사용자는 똑같을 것이고, content만 수정될 것이므로, id와 content만 받는다..
    public static AnswerDto of(Long id, String content) {
        return new AnswerDto(id,null, null, content,  null, null, null, null);
    }


    public static AnswerDto from(Answer answer) {
        return new AnswerDto(
                answer.getId(),
                answer.getQuestion(),
                answer.getUserAccount(),
                answer.getContent(),
                answer.getCreatedAt(),
                answer.getCreatedBy(),
                answer.getModifiedAt(),
                answer.getModifiedBy()
        );
    }

    public Answer toEntity() {  // entity는 각각으로 존재해야 하기 떄문에. instance가 아님.
        return Answer.of(
                content,
                userAccount,
                question
        );
    }

}


