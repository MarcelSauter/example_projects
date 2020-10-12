package com.example.Vue_Uebungsprojekt_BE.service;

import com.example.Vue_Uebungsprojekt_BE.model.Project;
import com.example.Vue_Uebungsprojekt_BE.model.User;
import com.example.Vue_Uebungsprojekt_BE.repository.ProjectRepository;
import com.example.Vue_Uebungsprojekt_BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByKeycloakId(String userIdKeycloak) {
        return this.userRepository.findByUserIdKeycloak(userIdKeycloak);
    }

    public Iterable<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }

    public Iterable<Project> findAllByProjectMember(String userIdKeycloak) {
        return this.projectRepository.findByProjectMember(this.findUserByKeycloakId(userIdKeycloak).get());
    }

    public Iterable<Project> findAllByProjectOwner(String userIdKeycloak) {
        return this.projectRepository.findByProjectOwner(this.findUserByKeycloakId(userIdKeycloak).get());
    }

    public Project createNewProject(Project project) {
        return this.projectRepository.save(project);
    }

    public void deleteProjectById(Long id) {
        this.projectRepository.deleteById(id);
    }
}
