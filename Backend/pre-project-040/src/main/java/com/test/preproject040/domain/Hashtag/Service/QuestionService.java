package com.test.preproject040.domain.Hashtag.Service;

import com.test.preproject040.domain.Hashtag.DTO.QuestionSaveRequestDTO;
import com.test.preproject040.domain.Hashtag.Repository.HashtagRepository;
import com.test.preproject040.domain.Hashtag.Repository.HashtagVariableRepository;
import com.test.preproject040.domain.Hashtag.Repository.QuestionRepository;
import com.test.preproject040.domain.Hashtag.model.Hashtag;
import com.test.preproject040.domain.Hashtag.model.HashtagVariable;

import com.test.preproject040.domain.Hashtag.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private HashtagVariableRepository hashtagVariableRepository;

    public void save(QuestionSaveRequestDTO questionSaveRequestDTO, HttpSession httpSession, List<String> tagNames) {
        //QuestionSaveRequestDTO.setWriter(accountID)
        Question question= questionRepository.save(questionSaveRequestDTO.toEntity());

        for(String name: tagNames){
            Hashtag hashtag=questionRepository.findHashtagByName(name).orElseThrow(()->new RuntimeException("해당 해시태그가 없습니다."));
            hashtagVariableRepository.save(new HashtagVariable(hashtag,question));

        }
    }

    public Iterable<Question> findAll(){
        return questionRepository.findAll();
    }

    public Iterable<Hashtag> findHashTags() {
        //hahtag 조회 기능
        return questionRepository.findHashtags();
    }

    public Iterable<Question> findAllByHashTag(String tagName) {
        return questionRepository.findAll().stream()
                .filter(question -> question.hasTag(tagName))
                .collect(Collectors.toList());
    }
}
