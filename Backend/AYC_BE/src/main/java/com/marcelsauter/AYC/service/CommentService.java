package com.marcelsauter.AYC.service;

import com.marcelsauter.AYC.model.Comment;
import com.marcelsauter.AYC.model.Question;
import com.marcelsauter.AYC.repository.CommentRepository;
import com.marcelsauter.AYC.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Iterable<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }

    public Comment insertNewComment() {
        Optional<Question> question = this.questionRepository.findById((long) 1);
        Comment comment = new Comment();

        comment.setCommentContent("Test Comment Content");
        comment.setUser(question.get().getUser());
        comment.setQuestion(question.get());

        return this.commentRepository.save(comment);
    }
}
