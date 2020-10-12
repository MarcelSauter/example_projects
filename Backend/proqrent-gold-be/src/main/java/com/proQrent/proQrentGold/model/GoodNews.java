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
public class GoodNews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private LocalDate date;
    private Double influence;

    public GoodNews(String title, String description, LocalDate date, Double influence) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.influence = influence;
    }
}
