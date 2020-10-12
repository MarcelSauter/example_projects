package com.proQrent.proQrentGold.service;

import com.proQrent.proQrentGold.model.Employee;
import com.proQrent.proQrentGold.repository.EmployeeRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BaseDataService baseDataService;

    public Iterable<Employee> getAllEmployees(String timePeriodString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate timePeriod = LocalDate.parse(timePeriodString, formatter);
        return this.employeeRepository.findAllByTimePeriod(timePeriod);
    }

    public Iterable<Employee> savePlannedHours(Iterable<Employee> employees) {
        StreamSupport.stream(employees.spliterator(), false).forEach(
                employee -> employee.setTimePeriod(LocalDate.now().withDayOfMonth(1))
            );
        return this.employeeRepository.saveAll(employees);
    }

    @Scheduled(cron = "0 1 0 1 1/1 ?")
    public void savePlannedHoursAutomatically() {
        LocalDate timePeriod = LocalDate.now().withDayOfMonth(1);
        Iterable<Employee> employees = this.employeeRepository.findAllByTimePeriod(timePeriod.minusMonths(1));

        StreamSupport.stream(employees.spliterator(), false).forEach(
                employee -> this.employeeRepository.save(this.initializeEmployee(employee, timePeriod))
            );
    }

    private Employee initializeEmployee(Employee employee, LocalDate timePeriod) {
        Employee employeeClone = new Employee();
        employeeClone.setId(employee.getId());
        employeeClone.setFirstName(employee.getFirstName());
        employeeClone.setLastName(employee.getLastName());
        employeeClone.setEmail(employee.getEmail());
        employeeClone.setUsername(employee.getUsername());
        employeeClone.setPlannedHours(employee.getPlannedHours());
        employeeClone.setTimeCorrection(null);
        employeeClone.setTimePeriod(timePeriod);

        return employeeClone;
    }
}
