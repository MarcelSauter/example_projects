package com.marcelsauter.AYC.controller;

import com.marcelsauter.AYC.model.Comment;
import com.marcelsauter.AYC.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public Iterable<Comment> getAllComments() {
        return this.commentService.getAllComments();
    }

    @PostMapping("/comments/create")
    public Comment insertNewComment() {
        return this.commentService.insertNewComment();
    }
}
