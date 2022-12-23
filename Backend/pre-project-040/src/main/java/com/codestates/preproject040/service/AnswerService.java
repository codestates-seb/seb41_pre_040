package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Answer;
import com.codestates.preproject040.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Answer updateAnswer(Answer answer) {
        // 로직상...
        // 질문을 찾아야됨.
        // 앤서 아이디를 찾고, 있는지 확인 그래서 readOnly 하고,
        //


        Answer findAnswer = findVerifiedAnswer(answer.getId());

    }








    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(Long id) {

    }


}
