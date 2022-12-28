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
    public List<QuestionResponseDto> searchQuestions(String searchKeyword, Pageable pageable) {
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
        //QuestionDto 받아서 Question 엔티티로 바꿔줄 예정
        //엔티티에는 유저정보가 필요한데, 이 유저정보는 퀘스천의 유저정보여야하니까
        //유저레포지토리에서 getReferenceById로 검색(findById는 Optional 이여서 null 들어갈 수 있으니까 오류)
        //Id는 퀘스천 유저의 아이디이므로, questionDto에서 userAccountDto를 찾고 그 유저 아이디를 넣어줌
        Question question = questionDto.toEntity(userRepository.getReferenceById(questionDto.userAccountDto().userId()));

        //Question 엔티티객체를 Repository에 저장하고, 다시 컨트롤러에는 QuestionResponseDto 형식으로 보낼 예정
        //저장한 엔티티객체를 QuestionDto.from으로 묶어서 QuestionDto로 만들어주고,
        //QuestionDto를 QuestionResponseDto.from으로 묶어서 response 형식으로 변환해준다.
        return QuestionResponseDto.from(QuestionDto.from(questionRepository.save(question)));
    }

    // id로 찾기
//    public Question findQuestion(long id) {
//        return findVerifiedQuestion(id);
//    }
    //질문글 1개 찾기(리스폰스Dto로 변환)
    public QuestionResponseDto findQuestion(long id) {
        QuestionResponseDto question =
                QuestionResponseDto.from(
                    QuestionDto.from(
                            findVerifiedQuestion(id)
                    )
                );
        return question;
    }


    // 전체 목록
    public List<QuestionResponseDto> findQuestions(Pageable pageable) {
        Page<Question> pageQuestions = questionRepository.findAll(pageable);
        List<QuestionResponseDto> questions =
                pageQuestions.stream() //Stream<Question>
                        .map(QuestionDto::from) //Stream<QuestionDto>
                        .map(QuestionResponseDto::from) //Stream<QuestionResponseDto>
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
