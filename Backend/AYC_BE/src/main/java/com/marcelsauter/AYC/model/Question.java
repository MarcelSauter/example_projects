package com.marcelsauter.AYC.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties(value = {"questions", "comments"})
    private User user;

    private String title;
    private Type type;
    private Category category;
    private String description;
    private String errorMessage;
    private Byte[] snippets;
    private Privacy privacy;

    @ElementCollection(targetClass = Technologies.class)
    @Enumerated(EnumType.STRING)
    private Collection<Technologies> technologies;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @OneToMany(mappedBy = "question")
    @JsonIgnoreProperties(value = {"question"})
    private Set<Comment> comments;
}
