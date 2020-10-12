package com.proQrent.proQrentGold.service;

import com.proQrent.proQrentGold.model.Employee;
import com.proQrent.proQrentGold.model.GoldCourse;
import com.proQrent.proQrentGold.model.GoodNews;
import com.proQrent.proQrentGold.model.ReachedTime;
import com.proQrent.proQrentGold.repository.EmployeeRepository;
import com.proQrent.proQrentGold.repository.GoldCourseRepository;
import com.proQrent.proQrentGold.repository.ReachedTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

@Service
public class GoldCourseService {

    @Autowired
    private GoldCourseRepository goldCourseRepository;

    @Autowired
    private ReachedTimeRepository reachedTimeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GoodNewsService goodNewsService;

    private static final LocalDate CURRENT_DATE_START_OF_MONTH = LocalDate.now().withDayOfMonth(1);

    public GoldCourse getActualGoldCourse() {
        return this.goldCourseRepository.findByTimePeriod(this.CURRENT_DATE_START_OF_MONTH);
    }

    @Scheduled(cron = "0 0 12 10 1/1 ?")
    public GoldCourse calculateActualGoldCourse() {
        Double goldCourseValue = this.calculateGoldCourse();
        GoldCourse goldCourse = new GoldCourse(goldCourseValue, this.CURRENT_DATE_START_OF_MONTH);
        this.createGoodNews();
        return this.goldCourseRepository.save(goldCourse);
    }

    public Double calculateDifferenceGoldCoursePreviousMonth() {
        GoldCourse actualGoldCourse = this.goldCourseRepository.findByTimePeriod(this.CURRENT_DATE_START_OF_MONTH);
        GoldCourse previousGoldCourse = this.goldCourseRepository.findByTimePeriod(this.CURRENT_DATE_START_OF_MONTH.minusMonths(1));

        return actualGoldCourse.getGoldCourse() - previousGoldCourse.getGoldCourse();
    }

    public Iterable<GoldCourse> getGoldCoursesForDiagram() {
        return this.goldCourseRepository.findAll();
    }

    private Double calculateGoldCourse() {
        Integer plannedHours = this.getPlannedHours();
        Integer reachedHours = this.getReachedHours();
        return (reachedHours - plannedHours) * 0.5;
    }

    private Integer getPlannedHours() {
        Iterable<Employee> employees = this.employeeRepository.findAllByTimePeriod(this.CURRENT_DATE_START_OF_MONTH);
        Integer[] plannedHours = {0};

        StreamSupport.stream(employees.spliterator(), false).forEach(
                employee -> plannedHours[0] += employee.getTimeCorrection() != null ?  employee.getTimeCorrection() :  employee.getPlannedHours()
        );

        return plannedHours[0];
    }

    private Integer getReachedHours() {
        Iterable<ReachedTime> reachedTimeEntries = this.reachedTimeRepository.findAllByTimePeriod(this.CURRENT_DATE_START_OF_MONTH);
        Integer[] reachedHours = {0};

        StreamSupport.stream(reachedTimeEntries.spliterator(), false).forEach(
                reachedTime -> reachedHours[0] += reachedTime.getMinutes() / 60
        );

        return reachedHours[0];
    }

    private void createGoodNews() {
        Integer plannedHours = this.getPlannedHours();
        Integer reachedHours = this.getReachedHours();
        String title = "Überstunden";
        String description = "Diesen Monat haben wir" + plannedHours + "h gearbeitet";
        LocalDate localDate = LocalDate.now();
        Double influence = (reachedHours - plannedHours) * 0.5;
        GoodNews goodNews = new GoodNews(title, description, localDate, influence);

        if (reachedHours > plannedHours) this.goodNewsService.addGoodNews(goodNews);
    }
}
