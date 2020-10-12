package com.marcelsauter.AYC.repository;

import com.marcelsauter.AYC.model.Category;
import com.marcelsauter.AYC.model.Privacy;
import com.marcelsauter.AYC.model.Question;
import com.marcelsauter.AYC.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Iterable<Question> findAllByCategoryAndPrivacy(Category category, Privacy privacy);
}
