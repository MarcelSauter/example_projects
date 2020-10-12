package com.example.Vue_Uebungsprojekt_BE.controller;

import com.example.Vue_Uebungsprojekt_BE.model.Project;
import com.example.Vue_Uebungsprojekt_BE.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<Iterable<Project>> findAll() {
        return ResponseEntity
                .ok()
                .body(this.projectService.findAllProjects());
    }

    @GetMapping("/projects/project-member/{userIdKeycloak}")
    public ResponseEntity<Iterable<Project>> findAllByProjectMember(@PathVariable("userIdKeycloak") String userIdKeycloak) {
        return ResponseEntity
                .ok()
                .body(this.projectService.findAllByProjectMember(userIdKeycloak));
    }

    @GetMapping("projects/project-owner/{userIdKeycloak}")
    public ResponseEntity<Iterable<Project>> findAllByProjectOwner(@PathVariable("userIdKeycloak") String userIdKeycloak) {
        return ResponseEntity
                .ok()
                .body(this.projectService.findAllByProjectOwner(userIdKeycloak));
    }

    @PostMapping("/create-project")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
        return ResponseEntity
                .ok()
                .body(this.projectService.createNewProject(project));
    }

    @DeleteMapping("/delete-project/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("id") Long id) {
        this.projectService.deleteProjectById(id);
        return ResponseEntity
                .ok()
                .body("Succesfully deleted Project with the id: " + id);
    }
}
