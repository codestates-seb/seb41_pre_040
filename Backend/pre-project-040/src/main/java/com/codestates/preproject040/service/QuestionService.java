package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.domain.constant.SearchType;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<QuestionDto> searchQuestions(SearchType searchType, String searchKeyword, Pageable pageable) {
//        if (searchKeyword == null || searchKeyword.isBlank()) {
//            return questionRepository.findAll(pageable).map(QuestionDto::from);
//        }
        return switch (searchType) {
            case TITLE -> questionRepository.findByTitleContaining(searchKeyword, pageable).map(QuestionDto::from);
            case CONTENT -> questionRepository.findByContentContaining(searchKeyword, pageable).map(QuestionDto::from);
            case ID -> questionRepository.findByUserIdContaining(searchKeyword, pageable).map(QuestionDto::from);
            case NICKNAME -> questionRepository.findByUserNicknameContaining(searchKeyword, pageable).map(QuestionDto::from);
            case HASHTAG -> null; //일단 null
        };
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findQuestion(long id) {
        return findVerifiedQuestion(id);
    }

    @Query("SELECT * FROM Question ORDER BY createdAt DESC LIMIT 2")
    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("question-id").descending()));
    }

    public Question updateQuestion(Long id, Question question) {
        Question findQuestion = findVerifiedQuestion(id);

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
