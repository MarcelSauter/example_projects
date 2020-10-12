package com.proQrent.proQrentGold.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoldCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double goldCourse;
    private LocalDate timePeriod;

    public GoldCourse(Double goldCourse, LocalDate timePeriod) {
        this.goldCourse = goldCourse;
        this.timePeriod = timePeriod;
    }
}
