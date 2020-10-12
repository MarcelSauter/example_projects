package com.example.Vue_Uebungsprojekt_BE.service;

import com.example.Vue_Uebungsprojekt_BE.model.Comment;
import com.example.Vue_Uebungsprojekt_BE.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addNewComment(Comment comment) {
        return this.commentRepository.save(comment);
    }
}
