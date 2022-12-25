package com.test.preproject040.domain.Hashtag.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class HashtagVariable {

    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    @JoinColumn(name="hashtag_id")
    @NonNull
    private Hashtag hashtag;

    //생성하지 않은 엔티티 사용 주의
    @ManyToOne
    @JoinColumn(name = "question_id")
    @NonNull
    private Question question;




    public boolean match(String tagName){
        return this.hashtag.getTagName().equals(tagName);
    }


}
