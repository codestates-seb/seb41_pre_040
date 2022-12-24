package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }

    // 전체 목록 (매개변수로 page, size)
    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("id").descending()));
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
