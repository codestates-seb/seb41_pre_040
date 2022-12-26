package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.AuditingFields;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // 질문 검색(title, content 검색 결과를 합쳐서 createdAt 역순으로 정렬)
    public List<QuestionDto> searchQuestions(String searchKeyword, Pageable pageable) {
        Page<Question> byTitleContaining = questionRepository.findByTitleContaining(searchKeyword, pageable);
        Page<Question> byContent1Containing = questionRepository.findByContent1Containing(searchKeyword, pageable);
        Page<Question> byContent2Containing = questionRepository.findByContent2Containing(searchKeyword, pageable);

        List<Question> titleList = new ArrayList<>(byTitleContaining.stream().toList());
        List<Question> content1List = new ArrayList<>(byContent1Containing.stream().toList());
        List<Question> content2List = new ArrayList<>(byContent2Containing.stream().toList());

        // 중복 아닌 결과 합치기
        for(int i = 0; i < content1List.size(); i++) {
            if (!titleList.contains(content1List.get(i))) {
                titleList.add(content1List.get(i));
            }
        }

        for(int i = 0; i < content2List.size(); i++) {
            if (!titleList.contains(content2List.get(i))) {
                titleList.add(content2List.get(i));
            }
        }

        List<QuestionDto> searchList =
                titleList.stream()
                        .sorted(Comparator.comparing(AuditingFields::getCreatedAt).reversed())
                        .map(QuestionDto::from)
                        .collect(Collectors.toList());

        return searchList;
    }

    // 생성
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    // id로 찾기
    public Question findQuestion(long id) {
        return findVerifiedQuestion(id);
    }

    // 전체 목록
    public List<QuestionDto> findQuestions(Pageable pageable) {
        Page<Question> pageQuestions = questionRepository.findAll(pageable);
        List<Question> questions = pageQuestions.stream().toList();
        List<QuestionDto> questionDtos =
                questions.stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toList());

        return questionDtos;
    }

    // 수정 (매개변수 id로 질문 찾고 수정하는 흐름)
    public Question updateQuestion(Long id, Question question) {
        Question findQuestion = findVerifiedQuestion(id);

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent1())
                .ifPresent(findQuestion::setContent1);
        Optional.ofNullable(question.getContent2())
                .ifPresent(findQuestion::setContent2);

        return questionRepository.save(findQuestion);
    }

    // 삭제
    public void deleteQuestion(long id) {
        Question findQuestion = findVerifiedQuestion(id);

        questionRepository.delete(findQuestion);
    }

    public Question findVerifiedQuestion(long id) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(id);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }

}
