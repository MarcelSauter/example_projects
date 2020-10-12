package com.proQrent.proQrentGold.model;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeIdAndTimePeriodCompositeKey implements Serializable {
    private String id;
    private LocalDate timePeriod;
}
