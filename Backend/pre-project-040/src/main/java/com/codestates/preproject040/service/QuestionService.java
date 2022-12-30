package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.AuditingFields;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.response.QuestionResponseDto;
import com.codestates.preproject040.dto.response.QuestionWithAnswersResponseDto;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.AnswerRepository;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository,
                           UserRepository userRepository,
                           AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    //TODO : 댓글 내용 검색시, 질문글 제목과 해당 댓글 content 노출
    public Page<QuestionResponseDto> searchQuestions(String searchKeyword, Pageable pageable) {
        // 검색 결과가 담길 임시List
        List<Question> tempList = new ArrayList<>();

        // 질문 검색 (title, content1, content2 검색으로 해당 Question 담긴 List)
        List<Question> titleList = questionRepository.findByTitleContaining(searchKeyword);
        List<Question> content1List = questionRepository.findByContent1Containing(searchKeyword);
        List<Question> content2List = questionRepository.findByContent2Containing(searchKeyword);

        // 답변 검색 (content 검색으로 해당 Answer 담긴 List)
        List<Answer> contentList = answerRepository.findByContentContaining(searchKeyword);

        // 검색된 정보가 들어있는 Question을 중복되지 않도록 임시 List에 담는 과정
        for(Answer content : contentList) {
            if (!tempList.contains(content.getQuestion())) {
                tempList.add(content.getQuestion());
            }
        }
        for(Question title : titleList) {
            if (!tempList.contains(title)) {
                tempList.add(title);
            }
        }
        for(Question content1 : content1List) {
            if (!tempList.contains(content1)) {
                tempList.add(content1);
            }

        }
        for(Question content2 : content2List) {
            if (!tempList.contains(content2)) {
                tempList.add(content2);
            }
        }

        //검색 결과 없으면 예외 던지기
        if (tempList.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND);
        }

        // TODO : 페이지네이션 먹으면 정렬 없애기 ㅠ
        // List<Quetion>을 List<QuestionResponseDto>로 바꿔주고, 합친 리스트들이 createdAt 역순으로 정렬되게 변경
        List<QuestionResponseDto> searchList =
                tempList.stream()
                        .sorted(Comparator.comparing(AuditingFields::getCreatedAt).reversed())
                        .map(QuestionDto::from)
                        .map(QuestionResponseDto::from)
                        .collect(Collectors.toList());

        Page<QuestionResponseDto> searchPage = new PageImpl<>(searchList, pageable, searchList.size());

        return searchPage;
    }

    // 생성
    public QuestionResponseDto createQuestion(QuestionDto questionDto) {
        Question question =
                questionDto.toEntity(
                        userRepository.getReferenceById(questionDto.userAccountDto().userId()));

        return QuestionResponseDto.from(QuestionDto.from(questionRepository.save(question)));
    }
/*
    // 1개 찾기
    public QuestionWithAnswersResponseDto findQuestion(Long id) {
        QuestionWithAnswersResponseDto question =
                QuestionWithAnswersResponseDto.from(
                    QuestionDto.from(findVerifiedQuestion(id)));

        return question;
    }
 */
    // questionId가 주어지면 답변이 없는지 확인
    public boolean answerIsEmpty(Long id){

        return QuestionDto.from(findVerifiedQuestion(id)).answers().isEmpty();
    }

    // 1개 찾기 (answerIsEmpty가 true이면, 일반 ResponseDto로 반환)
    @Transactional
    public QuestionResponseDto findQuestion(Long id) {
        QuestionResponseDto question =
                QuestionResponseDto.from(QuestionDto.from(findVerifiedQuestion(id)));

        return question;
    }

    // 1개 찾기 (answerIsEmpty가 false이면, 일반 WithAnswersResponseDto로 반환)
    public QuestionWithAnswersResponseDto findQuestionWithAnswers(Long id) {
        QuestionWithAnswersResponseDto question =
                QuestionWithAnswersResponseDto.from(
                        QuestionDto.from(findVerifiedQuestion(id))
                );

        return question;
    }

    // 전체 목록
    public List<QuestionResponseDto> findQuestions(Pageable pageable) {
        Page<Question> pageQuestions = questionRepository.findAll(pageable);
        List<QuestionResponseDto> questions =
                pageQuestions.stream()
                        .map(QuestionDto::from)
                        .map(QuestionResponseDto::from)
                        .collect(Collectors.toList());

        return questions;
    }

    // 수정 (매개변수 id로 질문 찾고 수정하는 흐름)
    public QuestionResponseDto updateQuestion(Long id, QuestionDto questionDto) {
        Question findQuestion = findVerifiedQuestion(id);

        Optional.ofNullable(questionDto.title())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(questionDto.content1())
                .ifPresent(findQuestion::setContent1);
        Optional.ofNullable(questionDto.content2())
                .ifPresent(findQuestion::setContent2);

        return QuestionResponseDto.from(QuestionDto.from(questionRepository.save(findQuestion)));
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
