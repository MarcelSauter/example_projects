package com.marcelsauter.AYC.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    @JsonIgnoreProperties("users")
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = {"user", "comments"})
    private Set<Question> questions;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = {"user", "question"})
    private Set<Comment> comments;

    private String firstName;
    private String lastName;
    private String email;
    private String job;
}
