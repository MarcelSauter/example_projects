package com.proQrent.proQrentGold.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BcsCsv {

    @CsvBindByName
    private String firstname;
    @CsvBindByName
    private String lastname;
    @CsvBindByName
    private String email;
    @CsvDate("dd.MM.yyyy")
    @CsvBindByName
    private LocalDate date;
    @CsvBindByName
    private Integer minutes;
}
