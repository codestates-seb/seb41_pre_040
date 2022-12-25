package com.test.preproject040.domain;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String content;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private final Set<Answer> answers = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private final Set<QuestionHashtag> questionHashtags = new LinkedHashSet<>();

    private Question(String title, String content, UserAccount userAccount) {
        this.title = title;
        this.content = content;
        this.userAccount = userAccount;
    }

    public static Question of(String title, String content, UserAccount userAccount) {
        return new Question(title, content, userAccount);
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

}
