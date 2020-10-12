package com.example.Vue_Uebungsprojekt_BE.controller;

import com.example.Vue_Uebungsprojekt_BE.model.Project;
import com.example.Vue_Uebungsprojekt_BE.model.Task;
import com.example.Vue_Uebungsprojekt_BE.model.User;
import com.example.Vue_Uebungsprojekt_BE.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all-tasks/{userIdKeycloak}")
    public ResponseEntity<Iterable<Task>> getAllTasksByUser(@PathVariable("userIdKeycloak") String userIdKeycloak) {
        return ResponseEntity
                .ok()
                .body(taskService.getAllTasksByUser(userIdKeycloak));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable("taskId") Long taskId) {
        return ResponseEntity
                .ok()
                .body(taskService.getTaskById(taskId));
    }

    @GetMapping("/tasks/project/{projectId}")
    public ResponseEntity<Iterable<Task>> getAllTasksByProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity
                .ok()
                .body(taskService.findAllTasksByProject(projectId));
    }

    @PostMapping("/add-task")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        System.out.println("AddTaskController called");
        return ResponseEntity
                .ok()
                .body(taskService.addTask(task));
    }

    @GetMapping("/change-status/{status}/{taskId}")
    public ResponseEntity<Task> changeDoneStatus(@PathVariable("status") String status, @PathVariable("taskId") Long taskId) {
        return ResponseEntity
                .ok()
                .body(taskService.changeDoneStatus(status, taskId));
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId) {
        return ResponseEntity
                .ok()
                .body(taskService.deleteTask(taskId));
    }
}
