package com.example.Vue_Uebungsprojekt_BE.controller;

import com.example.Vue_Uebungsprojekt_BE.model.Comment;
import com.example.Vue_Uebungsprojekt_BE.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create-comment")
    public ResponseEntity<Comment> addNewComment(@RequestBody Comment comment) {
        return ResponseEntity
                .ok()
                .body(commentService.addNewComment(comment));
    }
}
