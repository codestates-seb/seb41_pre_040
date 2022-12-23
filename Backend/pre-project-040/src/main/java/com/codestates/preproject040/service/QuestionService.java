package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
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

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findQuestion(long id) {
        return findVerifiedQuestion(id);
    }

    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("question-id").descending()));
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getId());

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent())
                .ifPresent(findQuestion::setContent);

        return questionRepository.save(findQuestion);
    }

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
