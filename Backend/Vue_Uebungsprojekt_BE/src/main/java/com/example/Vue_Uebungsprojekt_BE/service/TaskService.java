package com.example.Vue_Uebungsprojekt_BE.service;

import com.example.Vue_Uebungsprojekt_BE.model.Project;
import com.example.Vue_Uebungsprojekt_BE.model.Task;
import com.example.Vue_Uebungsprojekt_BE.model.User;
import com.example.Vue_Uebungsprojekt_BE.repository.ProjectRepository;
import com.example.Vue_Uebungsprojekt_BE.repository.TaskRepository;
import com.example.Vue_Uebungsprojekt_BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Iterable<Task> getAllTasksByUser(String userIdKeycloak) {
        Optional<User> user = this.userRepository.findByUserIdKeycloak(userIdKeycloak);
        return this.taskRepository.findAllByAuthor(user.get());
    }

    public Optional<Task> getTaskById(Long taskId) {
        return this.taskRepository.findById(taskId);
    }

    public Iterable<Task> findAllTasksByProject(Long projectId) {
        return this.taskRepository.findAllByProject(this.projectRepository.findById(projectId).get());
    }

    public Task addTask(Task task) {
        return this.taskRepository.save(task);
    }

    public Task changeDoneStatus(String newStatus, Long taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);
        task.get().setStatus(newStatus);

        return this.taskRepository.save(task.get());
    }

    public String deleteTask(Long taskId) {
        this.taskRepository.deleteById(taskId);
        return taskId + " was deleted";
    }
}
