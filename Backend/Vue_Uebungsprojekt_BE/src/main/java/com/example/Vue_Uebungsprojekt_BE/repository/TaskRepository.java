package com.example.Vue_Uebungsprojekt_BE.repository;

import com.example.Vue_Uebungsprojekt_BE.model.Project;
import com.example.Vue_Uebungsprojekt_BE.model.Task;
import com.example.Vue_Uebungsprojekt_BE.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByAuthor(User user);
    Iterable<Task> findAllByProject(Project project);
}
