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
        Page<Question> byContentContaining = questionRepository.findByContentContaining(searchKeyword, pageable);

        List<Question> titleList = new ArrayList<>(byTitleContaining.stream().toList());
        List<Question> contentList = new ArrayList<>(byContentContaining.stream().toList());

        // 중복 아닌 결과 합치기
        for(int i = 0; i < contentList.size(); i++) {
            if (!titleList.contains(contentList.get(i))) {
                titleList.add(contentList.get(i));
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
        Optional.ofNullable(question.getContent())
                .ifPresent(findQuestion::setContent);

        return questionRepository.save(findQuestion);
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

}
