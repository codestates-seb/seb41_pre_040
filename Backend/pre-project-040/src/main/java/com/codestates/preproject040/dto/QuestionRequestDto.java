//package com.codestates.preproject040.dto;
//
//import com.codestates.preproject040.domain.QuestionHashtag;
//
//import java.time.LocalDateTime;
//
//public record QuestionRequestDto(
//        String title,
//        QuestionHashtag questionHashtag,
//        String content,
//        LocalDateTime modifiedAt,
//        String modifiedBy
//){
//    public static QuestionRequestDto of(
//            String title,
//            QuestionHashtag questionHashtag,
//            String content,
//            LocalDateTime modifiedAt,
//            String modifiedBy) {
//
//        return new QuestionRequestDto(
//                title, questionHashtag, content, null, modifiedBy);
//    }
//
//    public QuestionDto toDto() {
//        return QuestionDto.of(
//                title,
//                questionHashtag,
//                content,
//                modifiedBy
//        );
//    }
//}
