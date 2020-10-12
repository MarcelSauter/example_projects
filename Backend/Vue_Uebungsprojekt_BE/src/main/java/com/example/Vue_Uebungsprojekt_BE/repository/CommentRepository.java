package com.example.Vue_Uebungsprojekt_BE.repository;

import com.example.Vue_Uebungsprojekt_BE.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
