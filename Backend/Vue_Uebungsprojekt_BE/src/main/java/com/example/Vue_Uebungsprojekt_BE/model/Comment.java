package com.example.Vue_Uebungsprojekt_BE.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnoreProperties(value = { "projects", "projectOwner", "tasks" })
    private User user;

    @ManyToOne
    @JoinColumn(name = "taskId", nullable = false)
    @JsonIgnoreProperties(value = { "comments" })
    private Task task;

    private String comment;
}
