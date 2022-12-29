package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.AuditingFields;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.dto.answer.AnswerDto;
import com.codestates.preproject040.dto.response.QuestionResponseDto;
import com.codestates.preproject040.dto.response.QuestionWithAnswersResponseDto;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.AnswerRepository;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    // 질문 검색(title, content 검색 결과를 합쳐서 createdAt 역순으로 정렬)
    //TODO : 결과 없을 때 전체목록 나오고있는데, 에러 메시지 보여주고 전체목록으로 리다이렉션되게...?
    //TODO : 댓글 내용 검색시, 질문글 제목과 해당 댓글 content내용 노출 >> 현재 댓글 노출안되게 되어있어서 수정 필요
    public List<QuestionResponseDto> searchQuestions(String searchKeyword, Pageable pageable) {
        Page<Question> byTitleContaining = questionRepository.findByTitleContaining(searchKeyword, pageable);
        Page<Question> byContent1Containing = questionRepository.findByContent1Containing(searchKeyword, pageable);
        Page<Question> byContent2Containing = questionRepository.findByContent2Containing(searchKeyword, pageable);

        List<Question> titleList = new ArrayList<>(byTitleContaining.stream().toList());
        List<Question> content1List = new ArrayList<>(byContent1Containing.stream().toList());
        List<Question> content2List = new ArrayList<>(byContent2Containing.stream().toList());

        //TODO : 댓글 검색은되는데 페이지네이션이 적용되지 않음. >> 페이지네이션 되게 수정 필요
        List<Answer> contentList = answerRepository.findByContentContaining(searchKeyword);
        List<Long> questionIdList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        for(int i = 0; i < contentList.size(); i++) {
            Long questionId = contentList.get(i).getQuestion().getId();
            if (!questionIdList.contains(questionId)) {
                questionIdList.add(questionId);
                questionList.add(questionRepository.getReferenceById(questionId)); // 앤서담긴 quesitonEntity
            }
        }


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

        for(int i = 0; i < questionList.size(); i++) {
            if (!titleList.contains(questionList.get(i))) {
                titleList.add(questionList.get(i));
            }
        }

        List<QuestionResponseDto> searchList =
                titleList.stream()
                        .sorted(Comparator.comparing(AuditingFields::getCreatedAt).reversed())
                        .map(QuestionDto::from)
                        .map(QuestionResponseDto::from)
                        .collect(Collectors.toList());

        return searchList;
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
