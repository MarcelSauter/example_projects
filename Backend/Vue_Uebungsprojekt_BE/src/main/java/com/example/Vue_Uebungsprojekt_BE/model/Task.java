package com.example.Vue_Uebungsprojekt_BE.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnoreProperties(value = { "tasks", "projectOwner", "projects" })
    private User author;

    private String title;
    private String category;

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    @JsonIgnoreProperties("tasks")
    private Project project;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
            name = "user_task",
            joinColumns = { @JoinColumn(name = "taskId") },
            inverseJoinColumns = { @JoinColumn(name = "userId") }
    )
    @JsonIgnoreProperties(value = { "tasks", "teamMember", "projectOwner", "projects" })
    private Set<User> teamMember;

    @OneToMany(mappedBy = "task")
    @JsonIgnoreProperties(value = { "task" })
    private Set<Comment> comments;

    private String description;
    private String status;
}
