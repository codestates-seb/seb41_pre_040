package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.AnswerRepository;
import com.codestates.preproject040.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
//    private final UserAccountService userAccountService;
//    private final UserAccountRepository userAccountRepository;

    public AnswerService(AnswerRepository answerRepository,
                         QuestionService questionService,
                         QuestionRepository questionRepository/*,
                         UserAccountService userAccountService ,
                         UserAccountRepository userAccountRepository */) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
//        this.userAccountService = userAccountService;
        this.questionRepository = questionRepository;
    }

    public Answer createAnswer(Answer answer) {
        // 게시글 있는지 확인
        questionService.findVerifiedQuestion(answer.getQuestion().getId());
        return answerRepository.save(answer);
    }

    // 게시글 있는지
    // 답변 존재 확인
    public Answer updateAnswer(Answer answer) {
        // 게시글 확인
       questionService.findVerifiedQuestion(answer.getQuestion().getId());

        // 답글 존재 확인.
        findVerifiedAnswer(answer.getId());

        // 유저확인(답글을 수정하려는 사람이 해당 답글을 달은 사람이 맞는지 확인)
        // 확인한 답글에서 유저의 아이디가 있는지..
        answer.getUserAccount().getUserId().equals((findVerifiedAnswer(answer.getId()).getUserAccount().getUserId()));

        // 예외가 발생하지 않았다면, answer를 리턴
        return answer;
    }

    public void deleteAnswer(Answer answer) {
        // 답글 확인 해준다.
        findVerifiedAnswer(answer.getId());
        // 있으면 삭제 // 이 아래 코드가 실행되는 건 존재한다는 의미.
        answerRepository.deleteById(answer.getId());
    }

    public Answer findVerifiedAnswer(long id) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(id);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

 }