package com.proQrent.proQrentGold.repository;

import com.proQrent.proQrentGold.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Iterable<Employee> findAllByTimePeriod(LocalDate timePeriod);
}
