package com.marcelsauter.AYC.controller;

import com.marcelsauter.AYC.model.Question;
import com.marcelsauter.AYC.service.QuestionPublicQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionPublicQController {

    @Autowired
    private QuestionPublicQService questionPublicQService;

    @GetMapping("public-q/{category}")
    public Iterable<Question> findAllPublicQByCategory(@PathVariable("category") String category) {
        return questionPublicQService.findAllPublicQByCategory(category);
    }

    @GetMapping("public-q/detail/{id}")
    public ResponseEntity<Question> findPublicQById(@PathVariable("id") Long id) {
        return this.questionPublicQService.findPublicQById(id)
                .map(question -> ResponseEntity
                    .ok()
                    .body(question))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/question/create")
    public Question insertNewQuestion() {
        return this.questionPublicQService.insertNewQuestion();
    }
}
