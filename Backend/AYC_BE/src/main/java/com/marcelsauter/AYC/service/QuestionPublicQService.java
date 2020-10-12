package com.marcelsauter.AYC.service;

import com.marcelsauter.AYC.model.*;
import com.marcelsauter.AYC.repository.QuestionRepository;
import com.marcelsauter.AYC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionPublicQService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Question> findAllPublicQByCategory(String category) {
        return this.questionRepository.findAllByCategoryAndPrivacy(Category.valueOf(category), Privacy.none);
    }

    public Optional<Question> findPublicQById(Long id) {
        return this.questionRepository.findById(id);
    }

    public Question insertNewQuestion() {
        Optional<User> user = this.userRepository.findById((long) 1);
        Question question = new Question();
        List<Technologies> technologies = new ArrayList<>();
        // Technologies technologies[] = new Technologies[] {Technologies.Angular, Technologies.Spring, Technologies.Java};

        technologies.add(Technologies.Angular);
        technologies.add(Technologies.Spring);
        technologies.add(Technologies.Java);


        question.setCategory(Category.informatics);
        question.setCreatedDateTime(LocalDateTime.now());
        question.setDescription("Test Description");
        question.setErrorMessage("Test Error Message");
        question.setSnippets(null);
        question.setType(Type.bug);
        question.setTitle("Test Title");
        question.setUser(user.get());
        question.setPrivacy(Privacy.none);
        question.setTechnologies(technologies);

        return this.questionRepository.save(question);
    }
}
