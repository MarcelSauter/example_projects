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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userIdKeycloak;
    private String username;
    private byte[] profilePicture;

    @OneToMany(mappedBy = "projectOwner")
    @JsonIgnoreProperties(value = { "projectOwner", "tasks", "projectMember" })
    private Set<Project> projectOwner;

    @ManyToMany(mappedBy = "projectMember", cascade = { CascadeType.MERGE })
    @JsonIgnoreProperties(value = { "projectMember", "projectOwner", "projectPicture", "tasks" })
    private Set<Project> projects;

    @ManyToMany(mappedBy = "teamMember", cascade = { CascadeType.MERGE })
    @JsonIgnoreProperties(value = { "teamMember", "projectPicture", "author", "project" })
    private Set<Task> tasks;

    public User(String userIdKeycloak, String username) {
        this.userIdKeycloak = userIdKeycloak;
        this.username = username;
    }
}
