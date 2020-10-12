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
public class ReachedTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer minutes;
    private String email;
    private LocalDate timePeriod;

    public ReachedTime(Integer minutes, String email, LocalDate timePeriod) {
        this.minutes = minutes;
        this.email = email;
        this.timePeriod = timePeriod;
    }
}
