package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.*;
import com.codestates.preproject040.dto.Hashtag.HashtagDto;
import com.codestates.preproject040.dto.Hashtag.HashtagResponseDto;
import com.codestates.preproject040.dto.question.QuestionDto;
import com.codestates.preproject040.dto.question.QuestionResponseDto;
import com.codestates.preproject040.dto.question.QuestionWithAnswersResponseDto;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final HashtagRepository hashtagRepository;
    private final UserAccountRepository userAccountRepository;
    private final AnswerRepository answerRepository;
    private final QuestionHashtagRepository questionHashtagRepository;

    public Page<QuestionResponseDto> searchQuestions(String searchKeyword, Pageable pageable) {
        // 검색 결과가 담길 임시List
        List<Question> tempList = new ArrayList<>();

        // 질문 검색 (title, content1, content2 검색으로 해당 Question 담긴 List)
        List<Question> titleList = questionRepository.findByTitleContaining(searchKeyword);
        List<Question> content1List = questionRepository.findByContent1Containing(searchKeyword);
        List<Question> content2List = questionRepository.findByContent2Containing(searchKeyword);

        // 답변 검색 (content 검색으로 해당 Answer 담긴 List)
        List<Answer> contentList = answerRepository.findByContentContaining(searchKeyword);

        // 검색된 정보 임시 List에 담는 과정
        for(Answer content : contentList) {
            tempList.add(content.getQuestion());
        }
        tempList.addAll(titleList);
        tempList.addAll(content1List);
        tempList.addAll(content2List);

        //검색 결과 없으면 예외 던지기
        if (tempList.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND);
        }

        // List<Quetion>을 List<QuestionResponseDto>로 바꿔주고, 중복 제거, 합친 리스트들이 createdAt 역순으로 정렬되게 변경

        List<QuestionDto> searchList =
                tempList.stream()
                        .distinct()
                        .sorted(Comparator.comparing(AuditingFields::getCreatedAt).reversed())
                        .map(QuestionDto::from).toList();

        List<QuestionResponseDto> questionResponse =
                searchList.stream()
                        .map(questionDto -> QuestionResponseDto.from(questionDto, returnHashtags(questionDto.questionId())))
                        .toList();

        // subList 이용해서 페이지네이션
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), questionResponse.size());
        List<QuestionResponseDto> subList = start >= end ? new ArrayList<>() : questionResponse.subList(start, end);
        Page<QuestionResponseDto> searchPage = new PageImpl<>(subList, pageable, questionResponse.size());

        return searchPage;
    }

    // 생성
    public QuestionResponseDto createQuestion(QuestionDto questionDto, List<HashtagDto> hashtags) {
        UserAccount user = userAccountRepository.getReferenceById(questionDto.userAccountDto().userId());
        Question question = questionRepository.save(questionDto.toEntity(user));

        log.info("question info : {}", question.getId());

        if (hashtags == null) {
            return QuestionResponseDto.from(QuestionDto.from(question));
        }
        for (HashtagDto dto : hashtags) {
            Hashtag hashtag = hashtagRepository.findByContent(dto.content())
                    .orElseGet(() -> hashtagRepository.save(dto.toEntity()));
            QuestionHashtag questionHashtag = QuestionHashtag.of(question, hashtag);
            questionHashtagRepository.save(questionHashtag);
        }

        List<HashtagResponseDto> hashtagList = returnHashtags(question.getId());
        return QuestionResponseDto.from(QuestionDto.from(question), hashtagList);
    }

    // questionId가 주어지면 답변이 없는지 확인
    public boolean answerIsEmpty(Long id){

        return QuestionDto.from(findVerifiedQuestion(id)).answers().isEmpty();
    }

    // 1개 찾기 (answerIsEmpty가 true이면, 일반 ResponseDto로 반환)
    @Transactional
    public QuestionResponseDto findQuestion(Long id) {


        QuestionResponseDto question =
                QuestionResponseDto.from(QuestionDto.from(findVerifiedQuestion(id)), returnHashtags(id));

        return question;
    }

    // 1개 찾기 (answerIsEmpty가 false이면, 일반 WithAnswersResponseDto로 반환)
    public QuestionWithAnswersResponseDto findQuestionWithAnswers(Long id) {

        QuestionWithAnswersResponseDto question =
                QuestionWithAnswersResponseDto.from(
                        QuestionDto.from(findVerifiedQuestion(id)),
                        returnHashtags(id)
                );

        return question;
    }

    // 전체 목록
    public List<QuestionResponseDto> findQuestions(Pageable pageable) {
        Page<Question> pageQuestions = questionRepository.findAll(pageable);
        List<QuestionDto> questions =
                pageQuestions.stream()
                        .map(QuestionDto::from)
                        .toList();

        return questions.stream()
                .map(questionDto -> QuestionResponseDto.from(questionDto, returnHashtags(questionDto.questionId())))
                .toList();
    }

    // 수정 (매개변수 id로 질문 찾고 수정하는 흐름)
    public QuestionResponseDto updateQuestion(Long id, QuestionDto questionDto, List<HashtagDto> hashtags) {
        Question findQuestion = findVerifiedQuestion(id);

        Optional.ofNullable(questionDto.title())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(questionDto.content1())
                .ifPresent(findQuestion::setContent1);
        Optional.ofNullable(questionDto.content2())
                .ifPresent(findQuestion::setContent2);
        questionRepository.save(findQuestion);

        if (hashtags != null) {
            questionHashtagRepository.deleteAllByQuestion_Id(id);
            for (HashtagDto dto : hashtags) {
                Hashtag hashtag = hashtagRepository.findByContent(dto.content())
                        .orElseGet(() -> hashtagRepository.save(dto.toEntity()));
                QuestionHashtag questionHashtag = QuestionHashtag.of(findQuestion, hashtag);
                questionHashtagRepository.save(questionHashtag);
            }
        }

        List<HashtagResponseDto> hashtagList = returnHashtags(id);
        return QuestionResponseDto.from(QuestionDto.from(findQuestion), hashtagList);
    }

    // 삭제
    public void deleteQuestion(Long id) {
        Question findQuestion = findVerifiedQuestion(id);
        questionRepository.delete(findQuestion);
    }

    public Question findVerifiedQuestion(Long id) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(id);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    private List<HashtagResponseDto> returnHashtags(Long questionId) {
        return questionHashtagRepository.findAllByQuestion_Id(questionId).stream()
                .map(questionHashtag -> hashtagRepository.getReferenceById(questionHashtag.getHashtag().getId()))
                .map(HashtagDto::from)
                .map(HashtagResponseDto::from)
                .toList();
    }



}
