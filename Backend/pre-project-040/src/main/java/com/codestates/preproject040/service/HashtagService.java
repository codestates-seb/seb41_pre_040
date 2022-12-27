package com.codestates.preproject040.service;

import com.codestates.preproject040.domain.Hashtag;
import com.codestates.preproject040.domain.QuestionHashtag;
import com.codestates.preproject040.dto.QuestionDto;
import com.codestates.preproject040.repository.HashtagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    //tagName 으로 hashtagRepository 검색 후 list 반환
    public List<Hashtag> searchTagName(QuestionHashtag tagName){
        List<Hashtag> list = hashtagRepository.findByTagNameContaining(tagName);
        return list;
    }

    // 리스르로 받았을 때는, 쪼개서 각 자 있는지 확인 ,없으면 신규 ID를 추가
    // 없는 ID면 새로운 ID 발급

    // 입력받은 해시태그 예시 {"가", "나", "다", "라"}
    public List<QuestionHashtag> createHashtag(QuestionDto questionDto) {
        //입력받은 List 값 가져오는 단계
        List<QuestionHashtag> list = questionDto.questionHashtag();

        //for문으로 List순회
        for(QuestionHashtag tagName: list) {
            //레포에 있는지 확인할 차례
            //레포에 해당하는 tagName 없으면(=처음  쓴 해시태그)
            if (searchTagName(name).isEmpty()) {
                //레포에 저장
                hashtagRepository.save(tag)
            }
        }
    }
}
