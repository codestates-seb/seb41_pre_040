package com.codestates.preproject040.domain;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Question extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter @Column(nullable = false)
    private String content1;

    @Setter @Column(nullable = false)
    private String content2;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private final Set<Answer> answers = new LinkedHashSet<>();

    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    public Set<QuestionHashtag> questionHashtags = new LinkedHashSet<>();

    private Question(String title, String content1, String content2, UserAccount userAccount, Set<QuestionHashtag> questionHashtag) {
        this.title = title;
        this.content1 = content1;
        this.content2 = content2;
        this.userAccount = userAccount;
        this.questionHashtags = questionHashtag;
    }

    public static Question of(String title, String content1, String content2, UserAccount userAccount, Set<QuestionHashtag> questionHashtag) {
        return new Question(title, content1, content2, userAccount, questionHashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        return id != null && id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    public boolean hasTag(String TagName){
        for (QuestionHashtag questionHashtag: questionHashtags){
            if(questionHashtag.getHashtag().getTagName().equals(TagName)){
                return true;
            }
        }
        return false;
    }

}
