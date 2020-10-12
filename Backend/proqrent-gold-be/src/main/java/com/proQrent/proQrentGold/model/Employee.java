package com.proQrent.proQrentGold.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@IdClass(EmployeeIdAndTimePeriodCompositeKey.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @Column(length = 200)
    private String id;

    @Id
    private LocalDate timePeriod;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Integer plannedHours;
    private Integer timeCorrection;
}
