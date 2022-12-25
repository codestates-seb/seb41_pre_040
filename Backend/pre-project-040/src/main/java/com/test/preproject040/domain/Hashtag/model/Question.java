package com.test.preproject040.domain.Hashtag.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    public List<HashtagVariable> hashtagVariables;

    //계정
    /*@ManyToOne
    private Account writer

     */

    @Builder
    public Question(String title, String url){
        this.title=title;
        this.url=url;
        //this.writer 계정
    }

    public Boolean hasTag(String tagName) {
        for (HashtagVariable hashtagVariable : hashtagVariables) {
            if (hashtagVariable.getHashtag().getTagName().equals(tagName)) {
                return true;
            }
        }
        return false;
    }
}
