package com.example.Vue_Uebungsprojekt_BE.repository;

import com.example.Vue_Uebungsprojekt_BE.model.Project;
import com.example.Vue_Uebungsprojekt_BE.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Iterable<Project> findByProjectMember(User projectMember);
    Iterable<Project> findByProjectOwner(User projectOwner);
}
