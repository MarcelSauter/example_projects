package com.marcelsauter.AYC.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = {"questions", "comments", "company"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnoreProperties(value = {"comments", "user"})
    private Question question;

    private String commentContent;

    @CreationTimestamp
    private LocalDateTime commentDateTime;
}
