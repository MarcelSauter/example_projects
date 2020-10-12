package com.proQrent.proQrentGold.controller;

import com.proQrent.proQrentGold.model.Employee;
import com.proQrent.proQrentGold.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/planned-hours/{timePeriod}")
    public ResponseEntity<Iterable<Employee>> getEmployeesPlannedHours(@PathVariable("timePeriod") String timePeriod) {
        return ResponseEntity
                .ok()
                .body(this.employeeService.getAllEmployees(timePeriod));
    }

    @PostMapping("/planned-hours")
    public ResponseEntity<Iterable<Employee>> savePlannedHours(@RequestBody Iterable<Employee> employees) {
        return ResponseEntity
                .ok()
                .body(this.employeeService.savePlannedHours(employees));
    }
}
