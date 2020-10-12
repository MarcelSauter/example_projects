package com.proQrent.proQrentGold.controller;

import com.proQrent.proQrentGold.model.GoldCourse;
import com.proQrent.proQrentGold.service.GoldCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/gold-course")
public class GoldCourseController {

    @Autowired
    private GoldCourseService goldCourseService;

    @GetMapping("/actual")
    public ResponseEntity<GoldCourse> getActualGoldCourse() {
        return ResponseEntity
                .ok()
                .body(this.goldCourseService.getActualGoldCourse());
    }

    @GetMapping("/difference-previous-month")
    public ResponseEntity<Double> getDifferencePreviousMonth() {
        return ResponseEntity
                .ok()
                .body(this.goldCourseService.calculateDifferenceGoldCoursePreviousMonth());
    }

    @GetMapping("/diagram")
    public ResponseEntity<Iterable<GoldCourse>> getGoldCourseListForDiagram() {
        return ResponseEntity
                .ok()
                .body(this.goldCourseService.getGoldCoursesForDiagram());
    }
}
