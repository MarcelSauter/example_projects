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
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnoreProperties(value = { "projectOwner", "projects", "tasks" })
    private User projectOwner;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "project_user",
            joinColumns = { @JoinColumn(name = "projectId") },
            inverseJoinColumns = { @JoinColumn(name = "userId") }
    )
    @JsonIgnoreProperties(value = { "projectOwner", "projects", "tasks"})
    private Set<User> projectMember;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties(value = { "project" })
    private Set<Task> tasks;
}
