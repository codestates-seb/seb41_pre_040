package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.domain.Question;
import com.codestates.preproject040.domain.UserAccount;
import com.codestates.preproject040.dto.answer.AnswerPatch;
import com.codestates.preproject040.dto.answer.AnswerPost;
import com.codestates.preproject040.exception.BusinessLogicException;
import com.codestates.preproject040.exception.ExceptionCode;
import com.codestates.preproject040.repository.AnswerRepository;
import com.codestates.preproject040.repository.QuestionRepository;
import com.codestates.preproject040.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
//    private final UserAccountService userAccountService;
    private final UserRepository userRepository;


    public UserAccount createUser() {
        return UserAccount.of("tmpUser", "1234", "abc@gmail.com", "임시 닉네임");
    }

    private UserAccount userAccount = this.createUser();


    public AnswerService(AnswerRepository answerRepository,
                         QuestionService questionService,/*
                         UserAccountService userAccountService ,*/
                         UserRepository userAccountRepository ) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.userRepository =  userAccountRepository;
//        this.userAccountService = userAccountService;
    }

    public Answer createAnswer(AnswerPost answerPost) /* , UserAccount userAccount */ {
        // 나중에 인증된 유저 받을 때, 매개변수로 UserAccount 같이 받기.
        // 유저는 인증이 된 상태이므로, 확인 굳이 안 해도 됨.
        // API계층에서 받아온 User그대로 유지.


        // Dto에서 엔티티로 바꾼 후, 바꾼 Answer를 DB에 넣는다.
        // 게시글 있는지 확인하면서, Answer를 save하면서 return
        return answerRepository.save(
                Answer.of(
                        answerPost.content(),
                        userAccount,
                        questionService.findVerifiedQuestion(
                                answerPost.questionId()
                            ) // qeustionService 괄호.
                        ) // Answer.of 괄호
        );  // Repo의 save 괄호
    }



    public Answer updateAnswer(AnswerPatch answerPatch) {
        // 게시글 확인
       questionService.findVerifiedQuestion(answerPatch.questionId());
        System.out.println("게시글 ok");


        // 같은 답글인지 확인.
        Answer findAnswer = findVerifiedAnswer(answerPatch.answerId());
        findAnswer.setContent(answerPatch.content());

        // 유저확인(답글을 수정하려는 사람이 해당 답글을 달은 사람이 맞는지 확인)
        // 확인한 답글에서 유저의 아이디가 있는지..
            // 원래 위의 답글엔티티에서 얻어온 UserAccount(findAnswer.getUserAccount().getUserId())와 비교해야하지만,
            // 일단은 더미유저로
        userRepository.findById(userAccount.getUserId());
        System.out.println("유저 ok");


        Optional.ofNullable(findAnswer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        System.out.println("answerPatch.answerId() = " + answerPatch.answerId());
        System.out.println("findAnswer.getId() = " + findAnswer.getId());


        return answerRepository.save(findAnswer);
    }



    public void deleteAnswer(Long questionId, Long answerId) /* , UserAccount userAccount */ {   // 인증된 유저랑 같이 받기.
        // 게시글 확인.
        questionService.findVerifiedQuestion(questionId);

        // 답글을 쓴 유저와 삭제를 시도하는 유저가 같은 유저인지 확인해준다.
        // 일단 나중에 User합칠 때,

        // 유저 확인 후, 맞다면 답글에 대한 유무를 확인하고,
        findVerifiedAnswer(answerId);
        // 있으면 삭제한다.
        // 이 아래 코드가 실행되는 건 존재한다는 의미.
        answerRepository.deleteById(answerId);
    }

    public Answer findVerifiedAnswer(Long id) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(id);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

 }