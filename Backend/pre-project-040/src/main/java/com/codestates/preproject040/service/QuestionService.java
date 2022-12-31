package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.AuditingFields;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    public final UserAccountRepository userAccountRepository;


    // 질문 검색(title, content 검색 결과를 합쳐서 createdAt 역순으로 정렬)
    @Transactional(readOnly = true)
    public List<QuestionDto> searchQuestions(String searchKeyword, Pageable pageable) {
        Page<Question> byTitleContaining = questionRepository.findByTitleContaining(searchKeyword, pageable);
        Page<Question> byContentContaining = questionRepository.findByContentContaining(searchKeyword, pageable);

        List<Question> titleList = byTitleContaining.stream().toList();
        List<Question> contentList = byContentContaining.stream().toList();

        // 중복 아닌 결과 합치기
        for (Question question : contentList)
            if (!titleList.contains(question))
                titleList.add(question);

        return titleList.stream()
                .sorted(Comparator.comparing(AuditingFields::getCreatedAt).reversed())
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }

    // 생성
    @Transactional
    public QuestionDto createQuestion(QuestionDto dto) {
        UserAccount user = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
        Question question = dto.toEntity(user);

        return QuestionDto.from(questionRepository.save(question));
    }

    // id로 찾기
    @Transactional(readOnly = true)
    public QuestionDto findQuestion(Long id) {
        return findVerifiedQuestion(id);
    }

    // 전체 목록
    @Transactional(readOnly = true)
    public List<QuestionDto> findQuestions(Pageable pageable) {
        Page<Question> pageQuestions = questionRepository.findAll(pageable);
        List<Question> questions = pageQuestions.stream().toList();
        return questions.stream()
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }

    // 수정 (매개변수 id로 질문 찾고 수정하는 흐름)
    public QuestionDto updateQuestion(Long questionId, QuestionDto dto) {
        Question question = questionRepository.getReferenceById(questionId);
        UserAccount user = userAccountRepository.getReferenceById(dto.userAccountDto().userId());

        Optional.ofNullable(question.getTitle())
                .ifPresent(question::setTitle);
        Optional.ofNullable(question.getContent())
                .ifPresent(question::setContent);

        return QuestionDto.from(questionRepository.save(question));
    }

    // 삭제
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public QuestionDto findVerifiedQuestion(Long id) {
        return questionRepository.findById(id)
                .map(QuestionDto::from)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

}
