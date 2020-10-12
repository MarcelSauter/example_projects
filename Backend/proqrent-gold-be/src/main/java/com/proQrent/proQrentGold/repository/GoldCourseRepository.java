package com.proQrent.proQrentGold.repository;

import com.proQrent.proQrentGold.model.GoldCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GoldCourseRepository extends CrudRepository<GoldCourse, Long> {
    GoldCourse findByTimePeriod(LocalDate timePeriod);
}
